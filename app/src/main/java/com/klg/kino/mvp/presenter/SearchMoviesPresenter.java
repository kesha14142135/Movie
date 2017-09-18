package com.klg.kino.mvp.presenter;

import com.klg.kino.R;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.contract.SearchMoviesContract;
import com.klg.kino.mvp.model.SearchMoviesBaseDataSource;
import com.klg.kino.mvp.model.SearchMoviesDataSource;

import java.util.List;

/**
 * Created by sergejkozin on 9/18/17.
 */

public class SearchMoviesPresenter implements SearchMoviesContract.Presenter {

    private SearchMoviesContract.View mView;
    private SearchMoviesBaseDataSource mDatabase;

    @Override
    public void attachView(SearchMoviesContract.View view) {
        mView = view;
        mDatabase = new SearchMoviesDataSource(view.getContext());
    }

    @Override
    public void detachView() {
        mDatabase.unsubscriber();
    }

    @Override
    public void getMovies(String movieName) {
        if (movieName.length() > 1) {
            mDatabase.getMovies(movieName, new SearchMoviesBaseDataSource.CallbackSearchMovies() {
                @Override
                public void onSuccess(List<MovieRealm> movies) {
                    mView.showMovies(movies);
                }

                @Override
                public void onFailure() {
                    mView.showError(mView.getContext().getString(R.string.error_get_movies));
                }
            });
        }
    }

    @Override
    public void addMovie(MovieRealm movieRealm) {
        mDatabase.addMovie(movieRealm, new SearchMoviesBaseDataSource.CallbackAddMovie() {
            @Override
            public void onSuccess() {
                mView.transitionMovieInfoScreen(movieRealm.getId());
            }

            @Override
            public void onFailure() {
                mView.getContext().getString(R.string.error_add_movies);
            }
        });
    }
}
