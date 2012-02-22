package com.peakxp.streaming_json;

class JSONArray extends JSONElement {
    private boolean done;
    private boolean hasStarted;

    public JSONArray() {
	hasStarted = false;
	done = false;
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