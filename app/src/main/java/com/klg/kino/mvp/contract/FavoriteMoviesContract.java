package com.klg.kino.mvp.contract;

import com.klg.kino.database.FavoriteRealm;
import com.klg.kino.database.MovieRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public interface FavoriteMoviesContract {

    interface View extends BaseContract.View {

        void showMovies(List<FavoriteRealm> movies);
    }

    interface Presenter extends BaseContract.Presenter<FavoriteMoviesContract.View> {

        void getMovies();
    }
}
