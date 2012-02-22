package com.peakxp.streaming_json;

class JSONString extends JSONElement {
    private StringBuilder sb;
    private boolean started;
    private boolean done;
    private boolean escape;

    public JSONString() {
	this.sb = new StringBuilder();
	this.started = false;
	this.done = false;
	this.escape = false;
    }

    /* for testing */
    protected JSONString(String s) {
	this();
	this.sb.append(s);
	this.started = true;
	this.done = true;
    }

    public char escapedChar(char c) {
	// thanks to douglascrockford for a rough approximation of this
	switch (c) {
	case '\\':
	case '"':
	    return c;
	case 'b':
	    return '\b';
	case 't':
	    return '\t';
	case 'n':
	    return '\n';
	case 'f':
	    return '\f';
	case 'r':
	    return '\r';
	default:
	    return ' ';
	    // need to handle unicode
	}
    }

    public boolean consume(char c) {
	if (!this.started && (c == '"')) {
	    this.started = true;
	} else if (!this.done && this.escape) {
	    this.escape = false;
	    sb.append( escapedChar(c) );
	} else if (!this.done && (c == '\\')) {
	    this.escape = true;
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
	return this.done;
    }

    public String getString() {
	return sb.toString();
    }
}