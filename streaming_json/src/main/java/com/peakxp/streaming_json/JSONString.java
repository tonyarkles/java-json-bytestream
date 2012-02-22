package com.peakxp.streaming_json;

class JSONString extends JSONElement {

    public boolean consume(char c) {
	return true;
    }

    public boolean isError() {
	return false;
    }

    public boolean isCompleted() {
	return false;
    }
}