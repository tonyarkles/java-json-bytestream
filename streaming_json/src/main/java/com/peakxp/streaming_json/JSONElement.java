package com.peakxp.streaming_json;

public abstract class JSONElement {
    abstract boolean consume(char c);
    abstract boolean isCompleted();
    abstract boolean isError();

    boolean addChild(JSONElement e) {
	throw new RuntimeException("addChild not implemented");
    }

    boolean takesChildren() {
	return false;
    }
}
