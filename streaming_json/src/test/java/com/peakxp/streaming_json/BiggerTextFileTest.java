package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.io.InputStream;

/**
 * Unit test for simple App.
 */
public class BiggerTextFileTest
    extends TestCase
{
    private StreamingJsonParser sjp;
    public void setupWithInputStream(InputStream in) {
	sjp = new StreamingJsonParser();
	// TODO
    }
    public void testMassiveArray() throws java.io.IOException {
	InputStream in = getClass().getClassLoader().getResourceAsStream("massive-array.txt");
	setupWithInputStream(in);
    }
}