package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class JSONNumberTest
    extends TestCase
{
    JSONNumber jsn;

    public void setupWithString(String s) {
	jsn = new JSONNumber();
	for(int i = 0; i < s.length(); i++) {
	    jsn.consume(s.charAt(i));
	}
    }

    public void testOne() {
	setupWithString("1");
	assertEquals(1, jsn.getInt());
    }

    public void testTwo() {
	setupWithString("2");
	assertEquals(2, jsn.getInt());
    }
}