package com.klg.kino.mvp.contract;

import com.klg.kino.database.MovieRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/18/17.
 */

public interface SearchMoviesContract {
    interface View extends BaseContract.View {

        void showMovies(List<MovieRealm> genres);

        void transitionMovieInfoScreen(Integer id);
    }

    interface Presenter extends BaseContract.Presenter<SearchMoviesContract.View> {

        void getMovies(String movieName);

        void addMovie(MovieRealm movieRealm);
    }
}
