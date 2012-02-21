
# Introduction

This software is meant to solve a specific problem I'm having: I'm receiving a bunch of JSON data from an async-IO socket and need to parse it as it comes in. There can be multiple JSON objects in this stream, with no delimiters between them. There's no guarantee that the chunks I'm receiving will be a complete object. 

# API

This is based off of the douglascrockford/JSON-java source, heavily modified.

The StreamingJsonParser interface is pretty simple. There are two methods:

    void parseBytes(byte[] input)

This will pass the byte array into the parser. If objects (either JSONObject or JSONArray) are available, you can read them out through the getParsed() method:

    List<Object> getParsed()



# Error Handling

This will come later.