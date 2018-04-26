package com.mobyinc.reststop;

import android.content.Context;

import java.util.Collection;

import io.reactivex.Single;

public class Store {
    RestAdaptable adapter;

    ICache cache;

    public Store(RestAdaptable adapter, Context context) {
        this.adapter = adapter;

        cache = new SharedPrefCache(context);
    }

    public Single<Boolean> startSession(String username, String password) {
        return startSession(username, password, null, null);
    }

    public Single<Boolean> startSession(String username, String password, String clientId, String clientSecret) {
        return adapter.authenticate(username, password, clientId, clientSecret).map(
                result -> {
                    return true;
                }
        );
    }

    public Boolean restoreSession() {
        return false;
    }

    public void refreshSession() {

    }

    public void endSession() {

    }
}
