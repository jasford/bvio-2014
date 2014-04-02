package com.bazaarvoice.bvio2014.dropwizard;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.yammer.metrics.core.HealthCheck;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Very simple health check for Datadog.  Datadog uses StatusPage.io to report the status of their service to the world
 * (i.e. their status page at <a href="http://status.datadoghq.com">status.datadoghq.com</a> is powered by
 * StatusPage.io).  So to monitor the health of Datadog we can just query the JSON form of their status from
 * <a href="http://status.datadoghq.com/index.json">here</a> and make sure that everything is healthy.  In order to
 * not overload StatusPage.io, we'll cache responses for up to 5 minutes before querying again.
 */
public class DatadogHealthCheck extends HealthCheck {
    private static final String URL = "http://status.datadoghq.com/index.json";

    private final Supplier<Result> result = Suppliers.memoizeWithExpiration(new Supplier<Result>() {
        final AsyncHttpClient client = new AsyncHttpClient();

        @Override
        public Result get() {
            String body;
            try {
                Response response = client.prepareGet(URL).execute().get(5, TimeUnit.SECONDS);
                body = response.getResponseBody();
            } catch (Exception e) {
                return Result.unhealthy(e);
            }

            JsonNode json;
            try {
                json = new ObjectMapper().readTree(body);
            } catch (IOException e) {
                return Result.unhealthy(e);
            }

            if (!json.has("status")) {
                return Result.unhealthy("Missing 'status' element in JSON response.");
            }

            JsonNode status = json.get("status");
            if (!status.has("description")) {
                return Result.unhealthy("Missing 'status.description' element in JSON response.");
            }

            String description = status.get("description").asText();
            if ("All Systems Operational".equals(description)) {
                return Result.healthy();
            } else {
                return Result.unhealthy(description);
            }
        }
    }, 5, TimeUnit.MINUTES);

    protected DatadogHealthCheck() {
        super("datadog");
    }

    @Override
    protected Result check() throws Exception {
        return result.get();
    }
}
