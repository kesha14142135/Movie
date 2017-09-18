package com.klg.kino.mvp.presenter;

import com.klg.kino.database.GenreRealm;
import com.klg.kino.mvp.contract.HomeContract;
import com.klg.kino.mvp.model.GenreBaseDataSource;
import com.klg.kino.mvp.model.GenreDataSource;

import java.util.List;

/**
 * Created by sergejkozin on 9/16/17.
 */

public class HomePresenter implements HomeContract.Presenter {
    private List<GenreRealm> mGenres = null;
    private HomeContract.View mView;
    private GenreBaseDataSource mDataSourceGenre;

    @Override
    public void attachView(HomeContract.View view) {
        mView = view;
        mDataSourceGenre = new GenreDataSource(mView.getContext());
    }

    @Override
    public void detachView() {
        mDataSourceGenre.unsubscribe();
    }

    @Override
    public void getGenres() {
        if (mGenres == null) {
            mGenres = mDataSourceGenre.getGenres();
        }
        mView.showGenres(mGenres);
    }

    @Override
    public void updateGenres(List<GenreRealm> genres) {
        mGenres = genres;
        mDataSourceGenre.updateGenres(genres);
    }

}
