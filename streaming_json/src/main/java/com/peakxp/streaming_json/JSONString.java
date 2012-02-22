package com.peakxp.streaming_json;

class JSONString extends JSONElement {
    private StringBuilder sb;
    private boolean started;
    private boolean done;

    public JSONString() {
	this.sb = new StringBuilder();
	this.started = false;
	this.done = false;
    }
    public boolean consume(char c) {
	if (!this.started && (c == '"')) {
	    this.started = true;
	} else if (!this.done && (c == '"')) {
	    this.done = true;
	} else {
	    sb.append(c);
	}
	return true;
    }

    public boolean isError() {
	return false;
    }

    public boolean isCompleted() {
	return false;
    }

    public String getString() {
	return sb.toString();
    }
}