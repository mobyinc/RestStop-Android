package com.mobyinc.reststop;

import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.Collection;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public interface RestAdaptable extends Disposable {
    //Create an auth response interface
    Single<JsonObject> authenticate(String username, String password, String clientId, String clientSecret);
    void setToken(String token);
    <T> Single<T> getOne(String resourceName, String id, Type type);
    <T> Single<Collection<T>> getList(String resourceName, Type type);
    <T> Single<Collection<T>> getList(String resourceName, String id, Type type);
}