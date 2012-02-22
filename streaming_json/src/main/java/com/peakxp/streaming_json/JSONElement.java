package com.peakxp.streaming_json;

abstract class JSONElement {
    abstract boolean consume(char c);
    abstract boolean isCompleted();
    abstract boolean isError();

    boolean addChild(JSONElement e) {
	// raise runtime error?
	return false;
    }

    boolean takesChildren() {
	return false;
    }
}
