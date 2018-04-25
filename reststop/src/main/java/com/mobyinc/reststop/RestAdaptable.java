package com.mobyinc.reststop;

import com.google.gson.JsonObject;

import io.reactivex.Single;

public interface RestAdaptable {
    //Create an auth response interface
    Single<JsonObject> authenticate(String username, String password, String clientId, String clientSecret);
    void setToken(String token);
    void getOne(String resourceName, String id);
}