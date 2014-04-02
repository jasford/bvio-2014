"""
    helloworld.py
    ~~~~~~~~~~~~~

    Provides a simple Tornado handler that hits the BV Data API.
    It's simply a small example of how to consume the API in tornado.
"""


import logging
import tornado.gen
import tornado.web
from tornado.options import define, options

import bvapi.client


log = logging.getLogger(__name__)

define("BAZAARVOICE_API_KEY", help="Bazaarvoice API Key", type=str)
define("BAZAARVOICE_API_HOST", help="Bazaarvoice API host", type=str)

class HelloWorldHandler(tornado.web.RequestHandler):
    @tornado.web.asynchronous
    @tornado.gen.engine
    def get(self):
        """Retrieves reviews from the API and renders info about them."""
        api = bvapi.client.BvApi(options.BAZAARVOICE_API_KEY, 
                options.BAZAARVOICE_API_HOST, staging=True)

        api_params = {
            'include': 'authors,products',
            'stats':   'reviews'  # to get product average overall rating
        }

        response = yield tornado.gen.Task(api.get_reviews, **api_params)

        total_num_reviews = response.TotalResults
        reviews = response.Results[:10]

        self.render('helloworld.html', total_num_reviews=total_num_reviews, reviews=reviews)


