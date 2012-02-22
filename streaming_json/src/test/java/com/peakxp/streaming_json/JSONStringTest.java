package com.peakxp.streaming_json;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class JSONStringTest
    extends TestCase
{
    JSONString jst;
    
    public void setupWithString(String s) {
	jst = new JSONString();
	jst.consume('"');
	for (int i = 0; i < s.length(); i++) {
	    jst.consume(s.charAt(i));
	}
	jst.consume('"');
    }

    public void testEmptyString() {
	setupWithString("");
	assertEquals( "", jst.getString() );
	assertTrue( jst.isCompleted() );
    }

    public void testWithOneCharacter() {
	setupWithString("a");
	assertEquals( "a", jst.getString() );
	assertTrue( jst.isCompleted() );
    }

    public void testWithMultipleCharacters() {
	setupWithString("foo bar baz");
	assertEquals( "foo bar baz", jst.getString());
	assertTrue( jst.isCompleted() );
    }

    public void testWithEscapedCharacters() {
	setupWithString("\\\"");
	assertEquals( "\"", jst.getString());
	assertTrue( jst.isCompleted() );
    }
}