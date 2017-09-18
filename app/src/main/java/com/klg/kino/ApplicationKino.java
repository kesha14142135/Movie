package com.klg.kino;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by sergejkozin on 8/19/17.
 */

public class ApplicationKino extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
