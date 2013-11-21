package org.deri.hujo.longMat2intMat;

import org.deri.hujo.longMat2intMat.Long2Int;

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
		String input = "test/in";
		String output = "test/out";
		String dict = "test/sdict";
		l2i.readMahoutSequenceFile(input, output, dict);
	}
}
