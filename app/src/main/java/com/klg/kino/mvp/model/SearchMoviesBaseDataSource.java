package com.klg.kino.mvp.model;

import com.klg.kino.database.MovieRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/18/17.
 */

public interface SearchMoviesBaseDataSource {

    void getMovies(String movieName, CallbackSearchMovies callBack);

    void addMovie(MovieRealm movie, CallbackAddMovie callback);

    interface CallbackSearchMovies {
        void onSuccess(List<MovieRealm> movies);

        void onFailure();
    }

    interface CallbackAddMovie {
        void onSuccess();

        void onFailure();
    }

    void unsubscriber();
}
