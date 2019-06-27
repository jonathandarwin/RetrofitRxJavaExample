package com.example.retrofitexample.common;

import org.json.JSONObject;

public interface APIHandler {
    void onResponse(JSONObject response);
    void onFailure(String call);
}
