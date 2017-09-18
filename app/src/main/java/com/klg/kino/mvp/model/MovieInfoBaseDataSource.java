package com.klg.kino.mvp.model;

import com.klg.kino.database.MovieRealm;

/**
 * Created by sergejkozin on 9/18/17.
 */

public interface MovieInfoBaseDataSource {

    void getMovie(int id, CallbackMovie callback);

    interface CallbackMovie {

        void onSuccess(MovieRealm movie, boolean flag);

        void onFailure();
    }

    void addMovie(MovieRealm movie, CallbackMovieChange callback);

    void deleteMovie(MovieRealm movie, CallbackMovieChange callback);

    interface CallbackMovieChange {

        void onSuccess(boolean isInFavorite);

        void onFailure();
    }

}
