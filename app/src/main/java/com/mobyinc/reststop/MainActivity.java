package com.mobyinc.reststop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    TextView display;

    RestAdaptable adaptable;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        adaptable = new DefaultRestAdapter("https://api.inaturalist.org/v1/", this);
        store = new Store(adaptable, this);

        //authenticationExample();

    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.dispose();
    }

    private void authenticationExample() {
        //This is an example of how you might authenticate a user using Rx & the adapter
        compositeDisposable.add(
                store.startSession("username", "password").subscribe(onSuccess -> {
                    //Do something
                }, throwable -> {
                    Log.e("AuthEx", throwable.getMessage());
                })
        );
    }
}
