package com.klg.kino.mvp.contract;

import com.klg.kino.database.FavoriteRealm;

/**
 * Created by sergejkozin on 9/18/17.
 */

public interface MovieInfoContract {
    interface View extends BaseContract.View {
        void showMovie(FavoriteRealm movies, boolean isInFavorite);

        void showChange(boolean isInFavorite);
    }

    interface Presenter extends BaseContract.Presenter<MovieInfoContract.View> {

        void getMovieById(int id);

        void addMovieInFavorites();

        void deleteMovieInFavorites();
    }
}
