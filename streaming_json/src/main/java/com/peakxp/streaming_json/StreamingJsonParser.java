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
	// return null if we should just ignore it, throw an exception if it's truly bad
	if ( (c == '-') || (c >= '0' && c <= '9') ) {
	    return new JSONNumber();
	}
	if (c == '{') {
	    return new JSONObject();
	}
	if (c == '[') {
	    return new JSONArray();
	}
	if (c == '"' || c == '\'') {
	    return new JSONString();
	}
	if (c == ' ' || c == '\n') {
	    return null;
	}
	throw new RuntimeException("Unknown character passed to guessType: '" + c + "'" + " top of stack is: " + stack.peek().toString());
    }

    void parseByte(char c) {
	/* for each byte:
	   - if stack is empty, guessType and push
	   - see if top of stack wants to consume the byte
	   - if not, see if it takes children; if yes, guessType and push
	*/
	if (stack.empty()) {
	    JSONElement n = guessType(c);
	    if (n == null) {
		return;
	    }
	    stack.push(n);
	}
	JSONElement el = stack.peek();
	if(!el.consume(c)) {
	    // what can this mean?
	    // - start of a child
	    // - end of an element (e.g. a number "123,"), in which case we need to pop it and keep going - ignore this case for now TODO since we're not worrying about those yet
	    if (el.isCompleted()) {
		stack.pop();
		JSONElement newtop = stack.peek();
		if (newtop.takesChildren()) {
		    newtop.addChild(el);
		    parseByte(c);
		    return;
		}
	    }

	    if (!el.takesChildren() && !el.isCompleted()) {
		throw new RuntimeException("Refused to take '" + c + "' but also does not take children.");
	    }
	    JSONElement n = guessType(c);
	    if (n == null) {
		return;
	    }
	    stack.push(n);
	    parseByte(c);
	}
	if (el.isCompleted()) {
	    JSONElement popped = stack.pop();
	    if (stack.empty()) {
		output.add(popped);
	    } else {
		stack.peek().addChild(popped);
	    }
	}
    }
    void parseBytes(char[] input) {
	parseBytes(input, input.length);
    }

    void parseBytes(char[] input, int count) {
	for (int i = 0; i < count; i++) {
	    parseByte(input[i]);
	}	
    }

    List<JSONElement> getParsed() {
	return output;
    }
}