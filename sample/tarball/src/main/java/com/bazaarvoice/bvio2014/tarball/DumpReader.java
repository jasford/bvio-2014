package com.bazaarvoice.bvio2014.tarball;

import com.bazaarvoice.jolt.JsonUtils;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Little command line app that reads the pre-scraped data, and write the
 * "Id" of the content to standard out.
 */
public class DumpReader {

    public static void main( String args[] ) throws IOException {

        ArgumentParser parser = ArgumentParsers.newArgumentParser( "dumpReader" )
                .description("Read on of the BVIO 2014 client dump files.");

        // first arg is always passkey
        parser.addArgument( "input" )
                .help( "Path to the input dump file. " )
                .type( Arguments.fileType().verifyExists().verifyIsFile().verifyCanRead() );

        BufferedReader dumpReader = null;
        try {

            Namespace res = parser.parseArgs(args);

            File input = res.get( "input" );
            dumpReader = new BufferedReader( new FileReader( input ) );

            String line = dumpReader.readLine();
            while (line != null) {

                Map<String, Object> content = JsonUtils.jsonToMap( line );


                //// HACKATHON TODO
                // Do something with the data
                String id = (String) content.get( "Id" );
                System.out.println(id);


                line = dumpReader.readLine();
            }
        }
        catch (ArgumentParserException e) {
            parser.handleError(e);
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        finally {
            if (dumpReader != null) {
                dumpReader.close();
            }
        }
    }
}