package org.deri.hujo.longMat2intMat;

import java.util.Map;

import org.apache.commons.cli.ParseException;
import org.deri.hujo.longMat2intMat.App;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws ParseException 
     */
    public void testValidOptions() throws ParseException
    {
        String line = "long2Int -input a -output b -dict c";
        String[] args = line.split(" ");
        Map<String, String> opts = App.validOptions(args);
        assertEquals(opts.get(App.INPUT), "a");
        assertEquals(opts.get(App.OUTPUT), "b");
        assertEquals(opts.get(App.DICTIONARY), "c");
    }
    
    public void testPrintHelp()
    {
    	App.printHelp();
    }
}
