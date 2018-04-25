package com.mobyinc.reststop;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.mobyinc.reststop.toolbox.JsonObjectRequest;

import io.reactivex.Single;

public class DefaultRestAdapter implements RestAdaptable {
    private String baseUrl;
    private RequestQueue queue;

    public DefaultRestAdapter(String baseUrl, Context context) {
        this.baseUrl = baseUrl;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public Single<JsonObject> authenticate(String username, String password, String clientId, String clientSecret) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("grant_type", "password");
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);

        return Single.create(emitter -> {
            JsonObjectRequest request = new JsonObjectRequest(
                    baseUrl,
                    jsonObject,
                    response -> emitter.onSuccess(response),
                    error -> emitter.onError(new Exception("error"))
            );
            queue.add(request);
        });
    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public void getOne(String resourceName, String id) {

    }
}
