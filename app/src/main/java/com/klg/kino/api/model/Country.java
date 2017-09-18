package com.klg.kino.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class Country {
    @SerializedName("iso_3166_1")
    @Expose
    private String mIso;
    @SerializedName("name")
    @Expose
    private String mName;

    public String getIso() {
        return mIso;
    }

    public void setIso(String iso) {
        mIso = iso;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
