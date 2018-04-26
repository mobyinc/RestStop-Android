package com.mobyinc.reststop;

import io.reactivex.Single;

public class Resource<T> {
    public String name;
    public RestAdaptable adapter;

    public Resource (String name, RestAdaptable adapter){
        this.name = name;
        this.adapter = adapter;
    }

    public Single<T> getOne(String id) {
        return adapter.getOne(name, id + "", getClass());
    }


}
