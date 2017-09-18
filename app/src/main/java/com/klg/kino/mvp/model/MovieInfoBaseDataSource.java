package com.klg.kino.mvp.model;

import com.klg.kino.database.FavoriteRealm;

/**
 * Created by sergejkozin on 9/18/17.
 */

public interface MovieInfoBaseDataSource {

    void getMovie(int id, CallbackMovie callback);

    interface CallbackMovie {

        void onSuccess(FavoriteRealm movie, boolean flag);

        void onFailure();
    }

    void addMovie(FavoriteRealm movie, CallbackMovieChange callback);

    void deleteMovie(FavoriteRealm movie, CallbackMovieChange callback);

    interface CallbackMovieChange {

        void onSuccess(boolean isInFavorite);

        void onFailure();
    }
}
