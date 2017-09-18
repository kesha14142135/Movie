package com.klg.kino.mvp.model;

import android.content.Context;

import com.klg.kino.R;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.contract.MovieInfoContract;

import java.util.List;

import io.realm.Realm;

/**
 * Created by sergejkozin on 9/18/17.
 */

public class MovieInfoDataSource implements MovieInfoBaseDataSource {
    private Context mContext;

    public MovieInfoDataSource(Context context) {
        mContext = context;
    }

    @Override
    public void getMovie(int id, CallbackMovie callback) {
        Realm realm = Realm.getDefaultInstance();
        MovieRealm movie = realm.where(MovieRealm.class).equalTo(mContext.getString(R.string.id), id).findFirst();
        callback.onSuccess(movie, false);
        realm.close();
    }

    @Override
    public void addMovie(MovieRealm movie, CallbackMovieChange callback) {
        callback.onSuccess(true);
    }

    @Override
    public void deleteMovie(MovieRealm movie, CallbackMovieChange callback) {
        callback.onSuccess(false);
    }
}
