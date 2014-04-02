package com.bazaarvoice.bvio2014.dropwizard;

import com.google.common.base.Optional;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.util.Duration;
import com.yammer.metrics.core.Metric;
import com.yammer.metrics.core.MetricName;
import com.yammer.metrics.core.MetricPredicate;
import com.yammer.metrics.reporting.DatadogReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A sample application for BV I/O 2014 that demonstrates how to write a simple Dropwizard application that will
 * publish metrics to Datadog using the Datadog API.
 *
 * @see <a href="http://dropwizard.codahale.com">Dropwizard Homepage</a>
 * @see <a href="http://www.datadoghq.com">Datadog Homepage</a>
 */
public class DatadogApplication extends Service<ApplicationConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(DatadogApplication.class);

    public static void main(String[] args) throws Exception {
        new DatadogApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
        bootstrap.setName("dropwizard-datadog");
    }

    @Override
    public void run(ApplicationConfiguration config, Environment env) throws Exception {
        // Wire up our application's resources and health checks
        env.addResource(CalculatorResource.class);
        env.addHealthCheck(new DatadogHealthCheck());

        // Publish metrics to Datadog
        registerWithDatadog(config.getDatadogConfiguration());
    }

    /** Register a metrics reporter that will publish metrics to Datadog, but only if we have Datadog configuration. */
    private void registerWithDatadog(Optional<DatadogConfiguration> config) {
        if (!config.isPresent()) {
            LOG.info("Datadog reporter is *NOT* enabled!");
            return;
        }

        DatadogConfiguration datadog = config.get();
        DatadogReporter reporter = new DatadogReporter.Builder()
                .withApiKey(datadog.getApiKey())
                .withHost(datadog.getHostname())
                .withVmMetricsEnabled(false)
                .withPredicate(NON_DROPWIZARD_CREATED_METRICS)
                .build();

        Duration frequency = datadog.getFrequency();
        reporter.start(frequency.getQuantity(), frequency.getUnit());
        LOG.info("Datadog reporter IS enabled!");
    }

    /**
     * By default Dropwizard creates several metrics to keep track of things like logging rates, http request rates,
     * http response codes as well as it's internal thread pool sizes.  While these are really useful for a production
     * application, they don't really add much value for a hackathon project.  Simplify things a bit by filtering these
     * metrics out (and save some work for Datadog to have to do).
     */
    private static final MetricPredicate NON_DROPWIZARD_CREATED_METRICS = new MetricPredicate() {
        @Override
        public boolean matches(MetricName name, Metric metric) {
            String group = name.getGroup();

            return !"ch.qos.logback.core".equals(group) &&
                   !"org.eclipse.jetty.server.nio".equals(group) &&
                   !"org.eclipse.jetty.servlet".equals(group) &&
                   !"org.eclipse.jetty.util.thread".equals(group);
        }
    };
}
