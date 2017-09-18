package com.klg.kino.mvp.contract;

import com.klg.kino.database.MovieRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public interface PopularMoviesContract {
    interface View extends BaseContract.View {

        void showMovies(List<MovieRealm> movies);

        void showMoreMovies(List<MovieRealm> movies);
    }

    interface Presenter extends BaseContract.Presenter<PopularMoviesContract.View> {

        void getMovies();

        void loadMoreMovies();

        void updateMoviesWithFilter();
    }
}
