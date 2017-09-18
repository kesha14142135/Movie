package com.klg.kino.mvp.model;

import android.content.Context;

import com.klg.kino.R;
import com.klg.kino.api.model.Movie;
import com.klg.kino.api.model.MoviesAnswer;
import com.klg.kino.database.IntegerRealm;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.retrofit.RetrofitProvider;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sergejkozin on 9/18/17.
 */

public class SearchMoviesDataSource implements SearchMoviesBaseDataSource {
    private RetrofitProvider mRetrofitProvider;
    private Subscription mSubscription;
    Context mContext;

    public SearchMoviesDataSource(Context context) {
        mContext = context;
        mRetrofitProvider = new RetrofitProvider(
                context.getString(R.string.base_url),
                context.getString(R.string.token));
    }

    @Override
    public void getMovies(String movieName, CallbackSearchMovies callback) {
        mSubscription = mRetrofitProvider.searchMovies(movieName)
                .map(this::getMoviesFromMoviesAnswer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> callback.onSuccess(
                                transformationMoviesToMoviesRealm(s)),
                        throwable -> callback.onFailure());
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

    private List<Movie> getMoviesFromMoviesAnswer(MoviesAnswer moviesAnswer) {
        return moviesAnswer.getMovie();
    }

    @Override
    public void addMovie(MovieRealm movie, CallbackAddMovie callback) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(movie));
        callback.onSuccess();
    }

    @Override
    public void unsubscriber() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }
}
