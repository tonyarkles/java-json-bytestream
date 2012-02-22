package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class JSONObjectTest
    extends TestCase
{
    public void testInitialState() {
	JSONObject j = new JSONObject();
	assertFalse(j.isCompleted());
	assertFalse(j.isError());
    }

    public void testConsumesAnEmptyObject() {
	JSONObject j = new JSONObject();

	assertTrue(j.consume('{'));
	assertFalse(j.isCompleted());
	assertTrue(j.consume('}'));
	assertTrue(j.isCompleted());
    }
}