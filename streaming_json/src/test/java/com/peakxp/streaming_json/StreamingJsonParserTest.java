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

    public void testOutputsTwoEmptyObjects() {
	setupWithParse("{}{}");
	List res = sjp.getParsed();

	assertEquals( 2, res.size());
	assertTrue( res.get(0) instanceof JSONObject );
	assertTrue( res.get(1) instanceof JSONObject );
    }

    public void testAcceptsAnEmptyArray() {
	setupWithParse("[]");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
	assertTrue( res.get(0) instanceof JSONArray );
    }

    public void testAcceptsAnArrayWithOneString() {
	setupWithParse("[\"foo\"]");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
	assertTrue( res.get(0) instanceof JSONArray );
	
	JSONArray arrayElem = (JSONArray)res.get(0);
	List<JSONElement> list = arrayElem.getList();
	assertTrue( list.get(0) instanceof JSONString );
	assertEquals("foo", ((JSONString)list.get(0)).getString());
    }

    public void testAcceptsAnArrayWithTwoStrings() {
	setupWithParse("[\"foo\",\"bar\"]");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
	assertTrue( res.get(0) instanceof JSONArray );

	JSONArray arr = (JSONArray)res.get(0);
	List<JSONElement> li = arr.getList();
	assertEquals( 2, li.size() );
	assertEquals( "foo", ((JSONString)li.get(0)).getString());
	assertEquals( "bar", ((JSONString)li.get(1)).getString());
    }

    public void testAcceptsAnEmptyArrayWithSpaces() {
	setupWithParse("[  ]");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
	assertTrue( res.get(0) instanceof JSONArray );
	JSONArray arr = (JSONArray)res.get(0);
	List<JSONElement> li = arr.getList();
	assertEquals( 0, li.size() );
    }

    public void testAcceptsAnArrayWithTwoStringsAndSpaces() {
	setupWithParse("[ \"foo\" , \"bar\" ]");
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
	assertTrue( res.get(0) instanceof JSONArray );

	JSONArray arr = (JSONArray)res.get(0);
	List<JSONElement> li = arr.getList();
	assertEquals( 2, li.size() );
	assertEquals( "foo", ((JSONString)li.get(0)).getString());
	assertEquals( "bar", ((JSONString)li.get(1)).getString());
    }

    public void testAcceptsAnArrayWithMultipleNumbers() {
	setupWithParse("[ 3.14, 6.02e23, 4 ]");
	List res = sjp.getParsed();
	JSONArray arr = (JSONArray)res.get(0);
	List<JSONElement> li = arr.getList();
	assertEquals( 3, li.size() );
	assertEquals( 3.14, ((JSONNumber)li.get(0)).getDouble(), 0.01 );
	assertEquals( 6.02e23, ((JSONNumber)li.get(1)).getDouble(), 0.01e23);
	assertEquals( 4, ((JSONNumber)li.get(2)).getInt());
    }

}