package com.peakxp.streaming_json;

class JSONObject extends JSONElement {
    private boolean hasStarted = false;
    private boolean done = false;

    public boolean consume(char c) {
	if (!hasStarted && (c == '{')) {
	    hasStarted = true;
	    return true;
	}
	if (hasStarted && (c == '}')) {
	    done = true;
	    return true;
	}
	return false;
    }

    public boolean isError() {
	return false;
    }

    public boolean isCompleted() {
	return done;
    }
}