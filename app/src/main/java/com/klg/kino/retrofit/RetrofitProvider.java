package com.klg.kino.retrofit;

import com.klg.kino.api.model.GenresAnswer;
import com.klg.kino.api.model.Movie;
import com.klg.kino.api.model.MoviesAnswer;
import com.klg.kino.api.service.KinoService;
import com.klg.kino.mvp.view.adapter.MoviesAdapter;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by sergejkozin on 8/29/17.
 */

public class RetrofitProvider {

    private String mBaseUrl;
    private String mToken;

    public RetrofitProvider(String baseUrl, String token) {
        mBaseUrl = baseUrl;
        mToken = token;
    }

    private Retrofit get() {
        return new Retrofit
                .Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Observable<GenresAnswer> getAllGenres() {
        return get().create(KinoService.class).getAllGenres(mToken)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }

    public Observable<MoviesAnswer> getAllMovie(String sort, int page) {
        return get().create(KinoService.class).getMovies(sort, page, mToken)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }

    public Observable<MoviesAnswer> getAllMovie(String sort, int page, String filter) {
        return get().create(KinoService.class).getMovies(sort, page, filter, mToken)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }
}
