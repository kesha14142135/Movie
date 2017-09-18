package com.klg.kino.mvp.model;

import com.klg.kino.database.FavoriteRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/18/17.
 */

public interface FavoriteBaseDataSource {

    void getMovies(CallbackMovies callback);

    interface CallbackMovies {
        void onSuccess(List<FavoriteRealm> movies);

        void onFailure();
    }
}
