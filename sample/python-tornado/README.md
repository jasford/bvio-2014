Example Bazaarvoice Tornado App
============================

Running Locally
---------------
# Download sample code

    git clone https://github.com/bazaarvoice/bvio2014.git

# Install virtualenv (if not already):

*Note*: python virtual environments are highly recommended to avoid conflicts between python projects.

    sudo easy_install pip
    pip install virtualenv

# Make a virtualenv

    virtualenv bvio2014-python

# Copy python code from git repo into virtualenv

    cp -R bvio2014/sample/python-tornado bvio2014-python/app
    
# Activate virtualenv

    cd bvio2014-python
    source bin/activate
    cd app

# Install python dependencies (now only into your virtualenv)

    pip install -r requirements.txt

# Modify server.conf as necessary (maybe need to update your API keys)

# Start server

    python server.py


