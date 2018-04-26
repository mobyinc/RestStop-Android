package com.mobyinc.reststop.toolbox;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import io.reactivex.SingleEmitter;

public class SingleRequest {
    SingleEmitter<String> stringEmitter;
    SingleEmitter<JsonObject> jsonEmitter;

    public SingleRequest(SingleEmitter<String> stringEmitter, SingleEmitter<JsonObject> jsonEmitter) {
        this.stringEmitter = stringEmitter;
    }

    public Request getRequest(String query) {
        return new StringRequest(
                Request.Method.GET,
                query,
                stringEmitter::onSuccess,
                error -> { /* use error extractor */ }
        );
    }

    public Request deleteRequest(String query) {
        return null;
    }

    public Request postRequest(String query) {
        return null;
    }

    public Request putRequest(String query) {
        return null;
    }
}
