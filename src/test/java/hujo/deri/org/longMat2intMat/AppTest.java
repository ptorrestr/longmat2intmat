package hujo.deri.org.longMat2intMat;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	private static final Logger log = Logger.getLogger(AppTest.class);
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
