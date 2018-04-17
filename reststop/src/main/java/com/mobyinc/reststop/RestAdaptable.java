package com.mobyinc.reststop;

import io.reactivex.Single;

public interface RestAdaptable {
    Single<Boolean> authenticate(String username, String password);
    void setToken(String token);
    void getOne(String resourceName, String id);
}