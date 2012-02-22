package com.peakxp.streaming_json;

class JSONNumber extends JSONElement {
 
    private StringBuffer sb;

    public JSONNumber() {
	this.sb = new StringBuffer();
    }
   
    public boolean consume(char c) {
	this.sb.append(c);
	return true;
    }
    public boolean isCompleted() {
	return false;
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