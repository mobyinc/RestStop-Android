package com.mobyinc.reststop;

import com.android.volley.Request;

import io.reactivex.Observable;

public interface IHttpClient {
    String send(Request<String> request);

}
