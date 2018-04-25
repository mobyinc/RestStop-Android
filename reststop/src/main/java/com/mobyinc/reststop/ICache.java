package com.mobyinc.reststop;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface ICache {
    void cacheSingleResult(String key, JsonObject jsonObject);
    void cacheListResult(String key, JsonArray jsonArray);

    JsonObject getSingle(String key);
    JsonArray getList(String key);
}
