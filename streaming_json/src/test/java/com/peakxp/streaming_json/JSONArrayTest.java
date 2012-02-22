package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class JSONArrayTest
    extends TestCase
{
    public void testInitialConditions() {
	JSONArray a = new JSONArray();
	assertFalse(a.isCompleted());
	assertFalse(a.isError());
    }

}