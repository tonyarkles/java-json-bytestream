package com.peakxp.streaming_json;

import java.util.Map;
import java.util.HashMap;

class JSONObject extends JSONElement {
    private boolean hasStarted = false;
    private boolean done = false;

    private JSONString key;
    private HashMap<String, JSONElement> map;
    protected enum LookingFor {
	KEY, VALUE
    }
    protected LookingFor lookingFor;

    public JSONObject() {
	this.map = new HashMap();
	this.lookingFor = LookingFor.KEY;
    }

    public boolean consume(char c) {
	if (!hasStarted && (c == '{')) {
	    hasStarted = true;
	    return true;
	}
	if (!hasStarted) {
	    return false;
	}
	if (c == '}') {
	    done = true;
	    return true;
	}
	if (c == ' ' || c == '\t' || c == '\n') {
	    return true;
	}
	if (c == ',' && lookingFor == LookingFor.KEY) {
	    return true;
	}
	if (c == ':' && lookingFor == LookingFor.VALUE) {
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

    public boolean addChild(JSONElement e) {
	if (key == null) {
	    key = (JSONString)e;
	    lookingFor = LookingFor.VALUE;
	} else {
	    this.map.put(key.getString(), e);
	    key = null;
	    lookingFor = LookingFor.KEY;
	}
	return true;
    }

    public boolean takesChildren() {
	return true;
    }

    public Map<String, JSONElement> getMap() {
	return map;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("JSONObject(");
	sb.append(this.map.toString());
	sb.append(")");
	return sb.toString();
    }
}