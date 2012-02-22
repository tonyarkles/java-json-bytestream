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
    public void testBlankObjectsOnInitialization() {
	StreamingJsonParser sjp = new StreamingJsonParser();
	List res = sjp.getParsed();
	assertEquals( 0, res.size() );
    }

    public void testSimpleObjectReturned() {
	StreamingJsonParser sjp = new StreamingJsonParser();
	sjp.parseBytes("{}".toCharArray());
	List res = sjp.getParsed();
	assertEquals( 1, res.size());
    }

}