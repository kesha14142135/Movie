package com.klg.kino.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class GenresAnswer {
        @SerializedName("genres")
        @Expose
        private List<Genre> mGenres = null;

        public List<Genre> getGenres() {
            return mGenres;
        }
        public void setGenres(List<Genre> genres) {
            mGenres = genres;
        }
}
