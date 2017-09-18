package com.klg.kino.database;

import io.realm.RealmObject;

/**
 * Created by sergejkozin on 9/17/17.
 */

public class IntegerRealm extends RealmObject{
    private Integer mId;

    public IntegerRealm() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
