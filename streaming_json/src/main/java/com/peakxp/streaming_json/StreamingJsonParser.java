package com.peakxp.streaming_json;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class StreamingJsonParser {
    private Stack<JSONElement> stack;

    private JSONElement guessType(char c) {
	if (c == '{') {
	    return new JSONObject();
	}
	return null;
    }

    void parseByte(char c) {
	/* for each byte:
	   - if stack is empty, guessType and push
	   - see if top of stack wants to consume the byte
	   - if not, see if it takes children; if yes, guessType and push
	*/
	if (stack.empty()) {
	    stack.push(guessType(c));
	}
    }
    void parseBytes(char[] input) {
	for (int i = 0; i < input.length; i++) {
	    parseByte(input[i]);
	}
    }

    List<Object> getParsed() {
	return new ArrayList();
    }
}