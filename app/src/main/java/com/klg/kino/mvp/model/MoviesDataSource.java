package com.klg.kino.mvp.model;

import android.content.Context;

import com.klg.kino.R;
import com.klg.kino.api.model.Movie;
import com.klg.kino.api.model.MoviesAnswer;
import com.klg.kino.database.GenreRealm;
import com.klg.kino.database.IntegerRealm;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.retrofit.RetrofitProvider;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sergejkozin on 9/17/17.
 */

public class MoviesDataSource implements MoviesBaseDataSource {
    private RetrofitProvider mRetrofitProvider;
    private Subscription mSubscription;
    private Context mContext;
    private int mPage;


    public MoviesDataSource(Context context) {
        mContext = context;
        mRetrofitProvider = new RetrofitProvider(
                context.getString(R.string.base_url),
                context.getString(R.string.token));
    }


    @Override
    public String getFiler() {
        Realm realm = Realm.getDefaultInstance();
        List<GenreRealm> genres = realm.where(GenreRealm.class).equalTo(mContext.getString(R.string.genre_checked), Boolean.TRUE).findAll();
        realm.close();
        return getFilterString(genres);
    }

    @Override
    public void getMovies(int page, CallbackMovies callback) {
        mSubscription = mRetrofitProvider.getAllMovie(mContext.getString(R.string.sort_popularity), page)
                .map(this::getMoviesFromMoviesAnswer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> callback.onSuccess(
                                addMoviesInLocalBase(s)),
                        throwable -> callback.onFailure());
    }

    @Override
    public void getMovies(int page, String filter, CallbackMovies callback) {
        mSubscription = mRetrofitProvider.getAllMovie(mContext.getString(R.string.sort_popularity), page, filter)
                .map(this::getMoviesFromMoviesAnswer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> callback.onSuccess(
                                addMoviesInLocalBase(s)),
                        throwable -> callback.onFailure());
    }

    @Override
    public void unsubscriber() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

    @Override
    public List<MovieRealm> getMoviesFromDatabase() {
        Realm realm = Realm.getDefaultInstance();
        List<MovieRealm> movies = realm.copyFromRealm(realm.where(MovieRealm.class).findAll());
        realm.close();
        return movies;
    }

    private String getFilterString(List<GenreRealm> genres) {
        String filer = "";
        for (GenreRealm genre : genres)
            filer += genre.getId() + ",";
        return filer;
    }

    private List<Movie> getMoviesFromMoviesAnswer(MoviesAnswer moviesAnswer) {
        mPage = moviesAnswer.getPage();
        return moviesAnswer.getMovie();
    }

    private List<MovieRealm> addMoviesInLocalBase(List<Movie> s) {
        if (mPage == 1) clearDatabase();
        List<MovieRealm> movies = new ArrayList<>();
        movies.addAll(transformationMoviesToMoviesRealm(s));
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm ->
                realm.insertOrUpdate(movies));
        realm.close();
        return movies;
    }

    private List<MovieRealm> transformationMoviesToMoviesRealm(List<Movie> s) {
        List<MovieRealm> movies = new ArrayList<>();
        MovieRealm baseMovie;
        IntegerRealm genre = new IntegerRealm();
        RealmList<IntegerRealm> genres = new RealmList<>();
        for (Movie movie : s) {
            baseMovie = new MovieRealm();
            baseMovie.setId(movie.getId());
            baseMovie.setVoteCount(movie.getVoteCount());
            baseMovie.setVideo(movie.getVideo());
            baseMovie.setVoteAverage(movie.getVoteAverage());
            baseMovie.setTitle(movie.getTitle());
            baseMovie.setPopularity(movie.getPopularity());
            baseMovie.setPosterPath(movie.getPosterPath());
            baseMovie.setOriginalLanguage(movie.getOriginalLanguage());
            baseMovie.setOriginalTitle(movie.getOriginalTitle());
            for (Integer g : movie.getGenreIds()) {
                genre.setId(g);
                genres.add(genre);
            }
            baseMovie.setGenreIds(genres);
            baseMovie.setBackdropPath(movie.getBackdropPath());
            baseMovie.setAdult(movie.getAdult());
            baseMovie.setOverview(movie.getOverview());
            baseMovie.setReleaseDate(movie.getReleaseDate());
            movies.add(baseMovie);
        }
        return movies;
    }

    private void clearDatabase() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmResults<MovieRealm> currentElements = realm1.where(MovieRealm.class).findAll();
            currentElements.deleteAllFromRealm();
        });
        realm.close();
    }

}
