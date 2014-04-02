package com.bazaarvoice.bvio2014.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.util.Duration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** Configuration parameters for how often to publish metrics to Datadog as well as which Datadog account to use. */
public class DatadogConfiguration {
    /** How often to publish metrics to Datadog. */
    @Valid @NotNull @JsonProperty
    private Duration frequency = Duration.minutes(1);

    /** The API key to use when publishing metrics to Datadog. */
    @Valid @NotNull @JsonProperty
    private String apiKey = null;

    /** The hostname to use when publishing metrics to Datadog. */
    @Valid @JsonProperty
    private String hostname = null;

    /** Return the frequency at which to publish metrics to Datadog. */
    public Duration getFrequency() {
        return frequency;
    }

    /** Return the API key to use when publishing metrics to Datadog. */
    public String getApiKey() {
        return apiKey;
    }

    /** Return the hostname to use when publishing metrics to Datadog. */
    public String getHostname() {
        return hostname;
    }
}
