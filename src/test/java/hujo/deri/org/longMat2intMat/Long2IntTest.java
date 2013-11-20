package hujo.deri.org.longMat2intMat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Long2IntTest extends TestCase {
	public Long2IntTest ( String testName ) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(Long2IntTest.class);
	}
	
	public void testFunction() {
		Long2Int l2i = new Long2Int();
		String input = "collection1/out";
		String output = "collection1_out";
		String dict = "collection1/sdict";
		l2i.readMahoutSequenceFile(input, output, dict);
	}
}
