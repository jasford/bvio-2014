BV.IO 2014
==========

Resources
---------
Here are some tools and data sources you can use in your hack, but don't feel restricted to just these.  Feel free to use whatever technology or information you can get your hands on!  If you're looking for some inspiration or have your eyes on the Customer Choice Award, checkout out some of these [ideas](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/bvio-ideas.pdf) provided by our clients.

Sample Applications
-------------------
Most APIs will have examples for you to check out or download, but we've provided [a few samples](sample) in this very repo.  Use these to jumpstart your brainstorming or to bootstrap your hack quickly.

BV Conversations API
--------------------
The Conversations API provides access to UGC data in the form of Reviews, Questions, Answers, Stories, Comments, and Profiles, as well as product catalog and statistical data, and allows submission of some of these types of data as well.  
* You'll need an API key to get access to a client's data.  Contact our Key Distributors and let them know which client you'll be hacking on.
* If you're interested in submitting content as part of your project, let us know and we'll hook you up with a staging key instead!
* The keys provided to you are rate limited, so don't be surprised if you run into trouble trying to download the entirety of Walmart's data set over _many_ requests.  Instead, why not check out the provided client data below?
* Check out the [documentation](https://developer.bazaarvoice.com/docs/read/conversations/) and [samples](https://developer.bazaarvoice.com/apis/conversations/tutorials/) to get started!

BV Full Client Data
-------------------
We know what you're thinking: "I want all the data!".  Before you swarm our API, check out the tarballs linked in the table below, which contain the entirety of our client's data.

| Client| Categories | Products | Reviews | Authors | Questions | Answers |
| ----- | ---------- | -------- | ------- | ------- | --------- | ------- |
| [Aveeno](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/aveeno.tgz)                 |         95 |      151 |    5876 |         |           |         |
| BestBuy  |    [9693](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/bestbuy_categories.tgz) | Pending | [1.7M (570MB)](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/bestbuy_reviews.tgz) | Pending | [194k (27MB)](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/bestbuy_questions.tgz) | [275k (42MB)](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/bestbuy_answers.tgz) |
| [Clean And Clear](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/cleanAndClear.tgz) |         10 |       94 |    2201 |         |           |         |
| [Dove](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/dove.tgz)                     |        124 |      250 |   18808 |   18548 |           |         |
| [Listerine](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/listerine.tgz)           |          6 |       24 |     316 |         |           |         |
| [Neosporin](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/neosporin.tgz)           |          4 |       16 |     167 |         |           |         |
| [Neutrogena](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/neutrogena.tgz)         |        258 |      355 |   16590 |         |           |         |
| [Macy's (232MB)](https://s3.amazonaws.com/nexus-public-artifacts/bvio2014/macys.tgz)  |      32701 |   454691 |  809252 |  483050 |     10421 |   15283 |

## Layout of the Data Tar Files

There are two types of files.

 - resourceType.txt : Each line of this file, is one piece of JSON content from the [BV Conversations Api](https://developer.bazaarvoice.com/docs/read/conversations/).
 - resourceType_ids.txt : A list of all the Ids of that data.  This file is an artifact of the data extraction process.

Note, the contents of the files should be considered _un-ordered_.  Also, a few of our clients have a lot of data (Bestbuy and Macy's), so we'll have a few flash drives available at the registration table with the data preloaded, for your convenience.

To bootstrap your progress, there is a sample Java program that can read the the supplied data [here](https://github.com/bazaarvoice/bvio2014/tree/master/sample/tarball).

Other APIs
----------

You're welcome to use any API available to you, but we wanted to mention a few that specifically requested to be a part of BV.IO 2014.  A number of these teams will be present throughout the event to help you and some are offering prizes for ingenious hacks using their service.  Be sure to check them out!

| Name | About | Links |
| ---- | ----- | ----- |
| [Datadog](http://www.datadoghq.com/) | Monitoring that helps turn data into actionable insight | [API](http://docs.datadoghq.com/api/) |
| [EagleEye Networks](http://www.eagleeyenetworks.com/) | Realtime video security feeds.  For access credentials, contact <mcotton@eagleeyenetworks.com> | [API](https://apidocs.eagleeyenetworks.com/apidocs/), [Sample](https://github.com/mcotton/watcher) |
| [FeedMagnet](http://www.feedmagnet.com/) | Provides brand focused experiences derived from social content |  |
| [Mashery](http://www.mashery.com/) | Connecting the world through better API management | [APIs](http://developer.mashery.com/apis), [XDK](http://xdk-software.intel.com/)|
| [Sematext](http://www.sematext.com/) | Proactive performance monitoring & anomaly detection | [Logsene](https://sematext.atlassian.net/wiki/display/PUBLOGSENE), [Events](https://sematext.atlassian.net/wiki/display/PUBSPM/Events+Integration), [Metrics](https://sematext.atlassian.net/wiki/display/PUBSPM/Custom+Metrics) |
| [Stripe](https://stripe.com/) | Making payments over the internet managable | [API](https://stripe.com/docs/api), [Docs](https://stripe.com/docs) |

Need Help?
----------
**IRC** - *#bvio2014* on Freenode (irc.freenode.net) or try this [web client](https://kiwiirc.com/client/irc.freenode.net/).

Presenting
----------
We'll provide a laptop already connected to our A/V setup, so you'll need to be able to access your project remotely while you present.  There are a number of ways to get a webservice running on your local machine ([Dropwizard](http://dropwizard.codahale.com/), [Node.js](http://nodejs.org/), and [Flask](http://flask.pocoo.org/) to name a few), but if you're looking for external hosting, consider using a [free-tier AWS](http://aws.amazon.com/free/) or [Heroku](https://www.heroku.com/new) account.

AWS
---
Additionally, Amazon Web Services has graciously offered $100 worth of [promotional credit](http://aws.amazon.com/activate/event/bazaarvoice) that can be applied to a new or existing account.
* Check out the [rules of use](https://aws.amazon.com/awscredits/) of AWS promotional credit.  For instance, credit is not applicable towards Reserved Instances.
* Amazon provides [many](http://aws.amazon.com/start-ups/) [resources](http://aws.amazon.com/resources/) to help you get started.
* Promotional credit is redeemable for any of these AWS offerings: EC2, Redshift, OpsWorks, Glacier, DynamoDB, S3, CloudFront, CloudSearch, ElastiCache, Route53, Elastic Transcoding Service, Simple Queue Service (SQS), Data Transfer, Elastic MapReduce, RDS, Elastic Beanstalk, Storage Gateway, Simple Notification Service (SNS), Simple Workflow Service (SWF), Simple Email Service (SES), CloudHSM, Data Pipeline, Data Transfer.
