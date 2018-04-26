package com.mobyinc.reststop;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mobyinc.reststop.toolbox.JsonObjectRequest;

import java.lang.reflect.Type;
import java.util.Collection;

import io.reactivex.Single;

public class DefaultRestAdapter implements RestAdaptable {
    final String requestTag = "restStop";
    private boolean disposed = false;

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
                    emitter::onSuccess,
                    error -> emitter.onError(new Exception("error"))
            );
            request.setTag(requestTag);
            queue.add(request);
        });
    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public <T> Single<T> getOne(String resourceName, String id, Type type) {
        return Single.create(emitter -> {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    baseUrl + resourceName + "/" + id,
                    response -> {
                        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                        emitter.onSuccess(new Gson().fromJson(jsonObject, type));
                    },
                    response -> emitter.onError(new Exception("Could not get object"))
            );
            request.setTag(requestTag);
            queue.add(request);
        });
    }

    public <T> Single<Collection<T>> getList(String resourceName, Type type) {
        return getList(resourceName, "", type);
    }

    @Override
    public <T> Single<Collection<T>> getList(String resourceName, String id, Type type) {
        String query = baseUrl + resourceName + "/" + id;
        return Single.create(emitter -> {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    query,
                    response -> {
                        JsonElement jsonElement = new JsonParser().parse(response);

                        emitter.onSuccess(new Gson().fromJson(jsonElement, type));
                    },
                    response -> emitter.onError(new Exception("Could not get object"))
            );
            request.setTag(requestTag);
            queue.add(request);
        });
    }

    @Override
    public void dispose() {
        queue.cancelAll(requestTag);
        disposed = true;
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
