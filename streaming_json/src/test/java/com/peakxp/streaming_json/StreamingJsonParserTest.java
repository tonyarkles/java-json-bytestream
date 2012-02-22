package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class StreamingJsonParserTest 
    extends TestCase
{
    private StreamingJsonParser sjp;
    public void setupWithParse(String s) {
	sjp = new StreamingJsonParser();
	sjp.parseBytes(s.toCharArray());
    }

    public void testBlankObjectsOnInitialization() {
	StreamingJsonParser sjp = new StreamingJsonParser();
	List res = sjp.getParsed();
	assertEquals( 0, res.size() );
    }

    public void testSimpleObjectReturned() {
	setupWithParse("{}");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
    }

    public void testDroppingSpaces() {
	setupWithParse(" { } ");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
    }

    public void testSingleStringKeyValueObject() {
	setupWithParse("{ \"foo\": \"bar\" }");
	List res = sjp.getParsed();

	assertEquals( 1, res.size());
	assertTrue( res.get(0) instanceof JSONObject );

	JSONObject jso = (JSONObject)res.get(0);

	assertEquals( 1, jso.getMap().size() );
    }

    public void testAcceptsAString() {
	setupWithParse("\"foo\"");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
	assertTrue( res.get(0) instanceof JSONString );	
    }
}