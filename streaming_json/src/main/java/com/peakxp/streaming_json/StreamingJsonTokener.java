package com.peakxp.streaming_json;

/*
Methods called by the parser embedded in JSONObject:

x.nextClean() - next character, skipping whitespace
x.back() - back up one character - used for look-ahead
x.nextValue() - parses a recursive value - string, json objects, json arrays, 
  or unquoted text that turns into a value (stringToValue)
x.next()

The distinction between the Tokenizer and the Parser is... pretty far
away. This isn't really based on a distinct tokenizer and
grammar/parser per-se, they're very inter-connected.

*/

/*
For containing state between invocations, we should keep a stack that keeps track of what we're currently working on building. Here are the options:

- string
- number
- object
- array
- true
- false
- null

Algorithmically, each of these will have a consume(char c), that will
return true or false based on whether or not that token was
consumed. Also has an isCompleted() and isError() method.

 */

public class StreamingJsonTokener {
    public void getBytes(byte[] input) {

    }
}