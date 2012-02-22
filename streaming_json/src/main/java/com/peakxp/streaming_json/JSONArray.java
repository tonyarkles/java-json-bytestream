package com.peakxp.streaming_json;

import java.util.ArrayList;

class JSONArray extends JSONElement {
    private boolean done;
    private boolean hasStarted;
    private ArrayList<JSONElement> elems;

    public JSONArray() {
	hasStarted = false;
	done = false;
	elems = new ArrayList<JSONElement>();
    }

    public boolean isError() {
	return false;
    }
    public boolean isCompleted() {
	return done;
    }

    public boolean consume(char c) {
	if (!hasStarted && (c == '[')) {
	    hasStarted = true;
	    return true;
	}
	if (hasStarted && (c == ']')) {
	    done = true;
	    return true;
	}
	return false;
    }
}