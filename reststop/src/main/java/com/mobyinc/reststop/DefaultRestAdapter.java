package com.mobyinc.reststop;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import io.reactivex.Single;

public class DefaultRestAdapter implements RestAdaptable {
    String baseUrl;
    RequestQueue queue;

    public DefaultRestAdapter(String baseUrl, Context context) {
        this.baseUrl = baseUrl;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public Single<Boolean> authenticate(String username, String password) {
        return null;
    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public void getOne(String resourceName, String id) {

    }
}
