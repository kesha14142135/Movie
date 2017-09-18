package com.klg.kino.mvp.presenter;

import com.klg.kino.R;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.contract.FavoriteMoviesContract;
import com.klg.kino.mvp.model.MoviesBaseDataSource;
import com.klg.kino.mvp.model.MoviesDataSource;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public class FavoriteMoviesPresenter implements FavoriteMoviesContract.Presenter, MoviesBaseDataSource.CallbackMovies {
    private FavoriteMoviesContract.View mView;
    private MoviesBaseDataSource mDataSource;
    private String mFilter;
    private List<MovieRealm> mMovies;
    private int mPage;
    private static final int START_READ_FROM = 1;

    public FavoriteMoviesPresenter() {
        mFilter = "";
        mPage = START_READ_FROM;
    }

    @Override
    public void attachView(FavoriteMoviesContract.View view) {
        mView = view;
        mDataSource = new MoviesDataSource(view.getContext());
        mFilter = mDataSource.getFiler();
        mMovies = mDataSource.getMoviesFromDatabase();
    }

    @Override
    public void detachView() {
        mDataSource.unsubscriber();
    }

    @Override
    public void getMovies() {
        if (mMovies.size() == 0) {
            mPage = START_READ_FROM;
            if (mFilter.equals("")) {
                mDataSource.getMovies(mPage, this);
            } else {
                mDataSource.getMovies(mPage, mFilter, this);
            }
        } else {
            mView.showMovies(mMovies);
        }
    }

    @Override
    public void loadMoreMovies() {
        mPage++;
        if (mFilter.equals("")) {
            mDataSource.getMovies(mPage, this);
        } else {
            mDataSource.getMovies(mPage, mFilter, this);
        }
    }

    @Override
    public void updateMoviesWithFilter() {
        mPage = START_READ_FROM;
        mMovies.clear();
        mFilter = mDataSource.getFiler();
        getMovies();
    }

    @Override
    public void onSuccess(List<MovieRealm> movies) {
        if (mPage == START_READ_FROM) {
            mView.showMovies(movies);
        } else {
            mView.showMoreMovies(movies);
        }
    }

    @Override
    public void onFailure() {
        if (mPage != START_READ_FROM)
            mPage--;
        mView.showError(mView.getContext().getString(R.string.error_get_movies));
    }
}
