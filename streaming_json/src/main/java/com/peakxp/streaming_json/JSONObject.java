package com.peakxp.streaming_json;

import java.util.Map;
import java.util.HashMap;

class JSONObject extends JSONElement {
    private boolean hasStarted = false;
    private boolean done = false;

    private JSONString key;
    private HashMap<String, JSONElement> map;

    public JSONObject() {
	this.map = new HashMap();
    }

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

    boolean addChild(JSONElement e) {
	if (key == null) {
	    key = (JSONString)e;
	} else {
	    this.map.put(key.getString(), e);
	}
	return true;
    }

    boolean takesChildren() {
	return true;
    }

    public Map<String, JSONElement> getMap() {
	return map;
    }
}