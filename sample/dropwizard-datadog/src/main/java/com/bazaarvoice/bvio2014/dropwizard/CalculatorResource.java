package com.bazaarvoice.bvio2014.dropwizard;

import com.sun.jersey.spi.resource.Singleton;
import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.MetricsRegistry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A simple Dropwizard service that acts as a RESTful implementation of a calculator.  It includes a Yammer Metrics
 * counter that keeps track of how many times each operation is performed.
 */
@Singleton
@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {
    private final MetricsRegistry registry = Metrics.defaultRegistry();
    private final Counter numAdds = registry.newCounter(getClass(), "num-adds");
    private final Counter numSubs = registry.newCounter(getClass(), "num-subs");
    private final Counter numMuls = registry.newCounter(getClass(), "num-muls");
    private final Counter numDivs = registry.newCounter(getClass(), "num-divs");

    @GET
    @Path("/add/{arg1}/{arg2}")
    public int add(@PathParam("arg1") int arg1, @PathParam("arg2") int arg2) {
        numAdds.inc();
        return arg1 + arg2;
    }

    @GET
    @Path("/sub/{arg1}/{arg2}")
    public int sub(@PathParam("arg1") int arg1, @PathParam("arg2") int arg2) {
        numSubs.inc();
        return arg1 - arg2;
    }

    @GET
    @Path("/mul/{arg1}/{arg2}")
    public int mul(@PathParam("arg1") int arg1, @PathParam("arg2") int arg2) {
        numMuls.inc();
        return arg1 * arg2;
    }

    @GET
    @Path("/div/{arg1}/{arg2}")
    public int div(@PathParam("arg1") int arg1, @PathParam("arg2") int arg2) {
        numDivs.inc();
        if (arg2 == 0) {
            return (arg1 < 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        return arg1 / arg2;
    }
}
