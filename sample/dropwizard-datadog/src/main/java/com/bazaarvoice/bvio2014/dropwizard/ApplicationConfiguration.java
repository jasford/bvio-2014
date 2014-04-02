package com.bazaarvoice.bvio2014.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * This is the main configuration class for your application.  You can add properties here to receive inputs from a
 * configuration file.  While this class strictly isn't necessary for this example, it makes for a nicer configuration
 * file to have related configuration items grouped together under a common prefix (i.e. datadog in this example).
 */
public class ApplicationConfiguration extends Configuration {
    /** Configuration for Datadog. */
    @Valid @NotNull @JsonProperty
    private Optional<DatadogConfiguration> datadog = Optional.absent();

    /** Return the configuration associated with Datadog. */
    public Optional<DatadogConfiguration> getDatadogConfiguration() {
        return datadog;
    }
}
