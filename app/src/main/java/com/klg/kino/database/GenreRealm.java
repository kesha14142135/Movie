package com.klg.kino.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class GenreRealm extends RealmObject {
    @PrimaryKey
    private int mId;
    private String mName;
    private boolean mIsChecked;

    public GenreRealm() {
    }

    public int getId() {
        return mId;
    }

    public boolean getIsChecked() {
        return mIsChecked;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setIsChecked(boolean isChecked) {
        mIsChecked = isChecked;
    }
}
