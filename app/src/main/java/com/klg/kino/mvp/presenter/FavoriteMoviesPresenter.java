package com.klg.kino.mvp.presenter;

import com.klg.kino.R;
import com.klg.kino.database.FavoriteRealm;
import com.klg.kino.mvp.contract.FavoriteMoviesContract;
import com.klg.kino.mvp.model.FavoriteBaseDataSource;
import com.klg.kino.mvp.model.FavoriteDataSource;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public class FavoriteMoviesPresenter implements FavoriteMoviesContract.Presenter, FavoriteBaseDataSource.CallbackMovies {
    private FavoriteMoviesContract.View mView;
    private FavoriteBaseDataSource mDataSource;

    public FavoriteMoviesPresenter() {
    }

    @Override
    public void attachView(FavoriteMoviesContract.View view) {
        mView = view;
        mDataSource = new FavoriteDataSource();
    }

    @Override
    public void detachView() {
    }

    @Override
    public void getMovies() {
        mDataSource.getMovies(this);
    }

    @Override
    public void onSuccess(List<FavoriteRealm> movies) {
        mView.showMovies(movies);
    }

    @Override
    public void onFailure() {
        mView.showError(mView.getContext().getString(R.string.error_get_movies));
    }
}
