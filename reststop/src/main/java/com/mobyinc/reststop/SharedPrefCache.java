package com.mobyinc.reststop;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SharedPrefCache implements ICache {
    private static final String PREF_FILENAME = "RestStopSharedPref";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPrefCache(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
    }

    @Override
    public void cacheSingleResult(String key, JsonObject jsonObject) {
        editor = sharedPreferences.edit();
        editor.putString(key, jsonObject.getAsString());
        editor.apply();
    }

    @Override
    public void cacheListResult(String key, JsonArray jsonArray) {
        editor = sharedPreferences.edit();
        editor.putString(key, jsonArray.getAsString());
        editor.apply();
    }

    @Override
    public JsonObject getSingle(String key) {
        String value = sharedPreferences.getString(key, "");
        return new JsonParser().parse(value).getAsJsonObject();
    }

    @Override
    public JsonArray getList(String key) {
        String value = sharedPreferences.getString(key, "");
        return new JsonParser().parse(value).getAsJsonArray();
    }
}