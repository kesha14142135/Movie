package com.klg.kino.mvp.presenter;

import com.klg.kino.R;
import com.klg.kino.database.FavoriteRealm;
import com.klg.kino.mvp.contract.MovieInfoContract;
import com.klg.kino.mvp.model.MovieInfoBaseDataSource;
import com.klg.kino.mvp.model.MovieInfoDataSource;

/**
 * Created by sergejkozin on 9/18/17.
 */

public class MovieInfoPresenter implements MovieInfoContract.Presenter, MovieInfoBaseDataSource.CallbackMovieChange {

    private MovieInfoContract.View mView;
    private FavoriteRealm mMovie;
    private MovieInfoBaseDataSource mBaseData;

    @Override
    public void attachView(MovieInfoContract.View view) {
        mMovie = new FavoriteRealm();
        mView = view;
        mBaseData = new MovieInfoDataSource(view.getContext());
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getMovieById(int id) {
        mBaseData.getMovie(id, new MovieInfoBaseDataSource.CallbackMovie() {
            @Override
            public void onSuccess(FavoriteRealm movie, boolean isInFavorite) {
                mMovie = movie;
                mView.showMovie(movie, isInFavorite);
            }

            @Override
            public void onFailure() {
                mView.showError(mView.getContext().getString(R.string.error_read_movie));
            }
        });
    }

    @Override
    public void addMovieInFavorites() {
        mBaseData.addMovie(mMovie, this);
    }

    @Override
    public void deleteMovieInFavorites() {
        mBaseData.deleteMovie(mMovie, this);
    }

    @Override
    public void onSuccess(boolean isInFavorite) {
        mView.showChange(isInFavorite);
    }

    @Override
    public void onFailure() {
        mView.showError(mView.getContext().getString(R.string.error_delete_or_add));
    }
}
