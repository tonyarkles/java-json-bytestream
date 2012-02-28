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
    
    public void setupWithString(String s, char startChar) {
	jst = new JSONString();
	jst.consume('"');
	for (int i = 0; i < s.length(); i++) {
	    jst.consume(s.charAt(i));
	}
	jst.consume('"');
    }

    public void setupWithString(String s) {
	setupWithString(s, '"');
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

    public void testWithEscapedQuote() {
	setupWithString("\\\"");
	assertEquals( "\"", jst.getString());
	assertTrue( jst.isCompleted() );
    }

    public void testWithEscapedNewline() {
	setupWithString("\\n");
	assertEquals("\n", jst.getString());
    }

    public void testWithAllEscapedCharacters() {
	setupWithString("\\\\ \\\" \\b \\t \\n \\f \\r");
	assertEquals("\\ \" \b \t \n \f \r", jst.getString());
    }
    public void testWithSingleQuoteString() {
	setupWithString("foo", '\'');
	assertEquals("foo", jst.getString());
    }
}