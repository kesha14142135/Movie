package com.klg.kino.mvp.model;

import com.klg.kino.database.MovieRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/17/17.
 */

public interface MoviesBaseDataSource {

    String getFiler();

    void getMovies(int page, CallbackMovies callback);

    void getMovies(int page, String filter, CallbackMovies callback);

    void unsubscriber();

    List<MovieRealm> getMoviesFromDatabase();

    interface CallbackMovies {
        void onSuccess(List<MovieRealm> movies);

        void onFailure();
    }

}
