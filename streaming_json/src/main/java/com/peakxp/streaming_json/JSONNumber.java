package com.peakxp.streaming_json;

public class JSONNumber extends JSONElement {
 
    private StringBuffer sb;
    private boolean done;

    public JSONNumber() {
	this.sb = new StringBuffer();
	this.done = false;
    }
   
    public boolean consume(char c) {
	if (c == ' ' || c == ',') {
	    this.done = true;
	    return false;
	}
	this.sb.append(c);
	return true;
    }
    public boolean isCompleted() {
	return done;
    }
    public boolean isError() {
	return false;
    }

    public int getInt() {
	return Integer.parseInt(this.sb.toString());
    }

    public double getDouble() {
	return Double.parseDouble(this.sb.toString());
    }
}