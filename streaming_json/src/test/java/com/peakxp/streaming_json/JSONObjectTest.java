package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.Map;

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

    public void testTakesChildren() {
	JSONObject j = new JSONObject();
	assertTrue(j.takesChildren());
    }

    public void testAddsChildrenIntoMap() {
	JSONObject j = new JSONObject();
	JSONString k = new JSONString("foo");
	JSONString v = new JSONString("bar");
	j.addChild(k);
	j.addChild(v);

	Map m = j.getMap();
	assertTrue(m.containsKey("foo"));
	assertEquals("bar", ((JSONString)m.get("foo")).getString());
    }

    public void testLookingFor() {
	JSONObject j = new JSONObject();
	JSONString k = new JSONString("foo");
	JSONString v = new JSONString("bar");

	assertEquals(JSONObject.LookingFor.KEY, j.lookingFor);
	j.addChild(k);
	assertEquals(JSONObject.LookingFor.VALUE, j.lookingFor);
	j.addChild(v);
	assertEquals(JSONObject.LookingFor.KEY, j.lookingFor);

    }
}