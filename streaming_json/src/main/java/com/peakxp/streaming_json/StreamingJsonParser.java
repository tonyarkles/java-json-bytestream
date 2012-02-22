package com.peakxp.streaming_json;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class StreamingJsonParser {
    private Stack<JSONElement> stack;
    private ArrayList<JSONElement> output;

    public StreamingJsonParser() {
	stack = new Stack<JSONElement>();
	output = new ArrayList<JSONElement>();
    }

    private JSONElement guessType(char c) {
	if (c == '{') {
	    return new JSONObject();
	}
	throw new RuntimeException("Unknown character passed to guessType: " + c);
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
	if(!stack.peek().consume(c)) {
	    // see about children here
	}
	if (stack.peek().isCompleted()) {
	    output.add(stack.pop());
	}
    }
    void parseBytes(char[] input) {
	for (int i = 0; i < input.length; i++) {
	    parseByte(input[i]);
	}
    }

    List<JSONElement> getParsed() {
	return output;
    }
}