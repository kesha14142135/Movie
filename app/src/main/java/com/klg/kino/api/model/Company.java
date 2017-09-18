package com.klg.kino.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class Company {
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("id")
    @Expose
    private Integer mId;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
