package com.peakxp.streaming_json;

import java.util.ArrayList;
import java.util.List;

class JSONArray extends JSONElement {
    private boolean done;
    private boolean hasStarted;
    private ArrayList<JSONElement> elems;

    public JSONArray() {
	hasStarted = false;
	done = false;
	elems = new ArrayList<JSONElement>();
    }

    public boolean isError() {
	return false;
    }
    public boolean isCompleted() {
	return done;
    }

    public boolean consume(char c) {
	if (!hasStarted && (c == '[')) {
	    hasStarted = true;
	    return true;
	}
	if (hasStarted && (c == ']')) {
	    done = true;
	    return true;
	}
	if (hasStarted && !elems.isEmpty() && (c == ',')) {
	    return true;
	}
	return false;
    }

    public boolean takesChildren() {
	return true;
    }

    public boolean addChild(JSONElement e) {
	elems.add(e);
	return true;
    }

    public List<JSONElement> getList() {
	return elems;
    }
}