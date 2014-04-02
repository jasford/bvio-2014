# Datadog example using Dropwizard

This application illustrates how to publish metrics to Datadog from a Dropwizard
application.  It's built using Java 1.7, but *should* run unchanged on Java 1.6.

The application is a simple RESTful calculator that can do *advanced* math
operations such as addition, subtraction, multiplication and division all
through a REST interface.

This application uses several open source libraries:

* [Dropwizard][dropwizard]
* [Yammer Metrics][metrics] (integrated into Dropwizard)
* [Jersey][jersey] (integrated into Dropwizard)
* [Coursera's Datadog Metrics Publisher][coursera]


## Building

This application is a standard maven project and should be very familiar to you
if you've used maven previously.  In order to build a jar invoke the
`mvn package` command.

This will produce a dependency reduced jar (e.g. one that includes all of its
dependencies within it) in the `target/` subdirectory.  If you don't change the
`artifactId` of the project, then the jar will be named
`dropwizard-datadog-0.1-SNAPSHOT.jar`.


## Running

By default the jar produced above can be run without any configuration (but it
won't publish metrics to Datadog).  Assuming you haven't changed the
application's `pom.xml` file you can invoke:

```bash
java -jar target/dropwizard-datadog-0.1-SNAPSHOT.jar server
```

If the application doesn't successfully start on your machine then one possible
problem may be that you already have something listening on ports 8080 and 8081.
If so, then consider stopping whatever other process you have, or read ahead to
the [Configuration](#configuration) section to see how to change which ports
Dropwizard binds to.

If your application does start up cleanly then you should be able to use the
calculator service via curl.

```bash
$ curl "localhost:8080/calculator/add/1/2"
3
$ curl "localhost:8080/calculator/sub/1/2"
-1
$ curl "localhost:8080/calculator/mul/20/4"
80
```

You can also query your server for metrics related to the `CalculatorResource`
(notice the port is `8081` in this command -- that's the port that Dropwizard
uses by default for administrative resources):

```
$ curl "localhost:8081/metrics?class=com.bazaarvoice.bvio2014&pretty=true"
{
  "com.bazaarvoice.bvio2014.dropwizard.CalculatorResource" : {
    "num-adds" : {
      "type" : "counter",
      "count" : 1
    },
    "num-divs" : {
      "type" : "counter",
      "count" : 0
    },
    "num-muls" : {
      "type" : "counter",
      "count" : 1
    },
    "num-subs" : {
      "type" : "counter",
      "count" : 1
    }
  }
}
```


## Configuration

If you wish to enable Datadog metric reporting or to change the HTTP port that
Dropwizard binds to, then you will need to provice a simple configuration file
to Dropwizard when you launch it.  Dropwizard configuration files are YAML files
(usually named `config.yaml`, but you're free to name it whatever you want)
that contain all of the configuration for your application.

The sample application extends the standard Dropwizard configuration to add an
additional `datadog` configuration section that can be used to provide
parameters for how metrics should be published to Datadog.  If that section is
missing from the configuration then metrics will be collected but not published
to Datadog.

Here is a small example `config.yaml` file that you can use.

```yaml
# This section is 100% optional, but useful if you need to have the application
# listen on a different set of ports.
http:
  port: 8080
  adminPort: 8081

# This section tells the application that it should publish metrics to Datadog
# and tells it how often to do so.  If this section is not present, then metrics
# will be collected by your application and visible on the admin port, but they
# won't be published to Datadog.
datadog:
  apiKey: <your api key here> # REQUIRED
  frequency: 1m               # OPTIONAL (Default: 1m)
  hostname: <your host name>  # OPTIONAL (Default: No hostname)
```

And to start your application using this `config.yaml` file:

```bash
java -jar target/dropwizard-datadog-0.1-SNAPSHOT.jar server config.yaml
```


[dropwizard]: http://dropwizard.codahale.com
[metrics]: http://metrics.codahale.com/
[jersey]: http://jersey.java.net
[coursera]: http://github.com/coursera/metrics-datadog
