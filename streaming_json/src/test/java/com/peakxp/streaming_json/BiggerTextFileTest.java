package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Unit test for simple App.
 */
public class BiggerTextFileTest
    extends TestCase
{
    private StreamingJsonParser sjp;
    public void setupWithInputStream(InputStream in) throws java.io.IOException {
	sjp = new StreamingJsonParser();
	InputStreamReader isr = new InputStreamReader(in);
	while (isr.ready()) {
	    char[] c = new char[1024];
	    int count = isr.read(c, 0, 1024);
	    sjp.parseBytes(c, count);
	}
    }
    public void testMassiveArray() throws java.io.IOException {
	InputStream in = getClass().getClassLoader().getResourceAsStream("massive-array.txt");
	setupWithInputStream(in);
    }
}