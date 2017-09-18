package com.klg.kino.mvp.presenter;

import com.klg.kino.mvp.contract.SplashContract;
import com.klg.kino.mvp.model.GenreBaseDataSource;
import com.klg.kino.mvp.model.GenreDataSource;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;
    private GenreBaseDataSource mDatabase;

    @Override
    public void attachView(SplashContract.View view) {
        mView = view;
        mDatabase = new GenreDataSource(view.getContext());
    }

    @Override
    public void detachView() {

    }

    @Override
    public void isFirstOpen() {
        if (mDatabase.isGenresTableIsNotEmpty()) {
            mView.transitionHomeScreen(true);
        } else {
            mDatabase.addGenres(new GenreBaseDataSource.CallbackGenres() {
                @Override
                public void onSuccess() {
                    mView.transitionHomeScreen(true);
                }

                @Override
                public void onFailure() {
                    mView.transitionHomeScreen(false);
                }
            });
        }
    }

}

