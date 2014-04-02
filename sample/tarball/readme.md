# Tarball Reader

If you want to do some analysis of the provided client data, you can start with this tool
to read all the data from the pre-scraped files.

## Usage

1\. Build the tool


    mvn clean install

2\. Download some source data and untar it.  (aveeno.tgz)


    tar xzvf aveeno.tgz

3\. Run the tool, which will print out all the content Ids of the data in those files.


    ./dumpReader.sh aveeno/reviews.txt
    ./dumpReader.sh aveeno/products.txt


4\. Hack the reader to do something more interesting with the data.

5\. Profit!
