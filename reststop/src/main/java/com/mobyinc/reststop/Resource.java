package com.mobyinc.reststop;

public class Resource<T> {
    public String name;
    public RestAdaptable adapter;

    public Resource (String name, RestAdaptable adapter){
        this.name = name;
        this.adapter = adapter;
    }
}
