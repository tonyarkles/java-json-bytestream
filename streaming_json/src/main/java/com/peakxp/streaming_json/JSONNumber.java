package com.peakxp.streaming_json;

class JSONNumber extends JSONElement {
    
    public boolean consume(char c) {
	return true;
    }
    public boolean isCompleted() {
	return false;
    }
    public boolean isError() {
	return false;
    }

    public int getInt() {
	return 1;
    }

}