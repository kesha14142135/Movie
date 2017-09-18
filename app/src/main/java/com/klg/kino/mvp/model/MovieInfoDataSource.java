package com.klg.kino.mvp.model;

import android.content.Context;

import com.klg.kino.R;
import com.klg.kino.database.FavoriteRealm;
import com.klg.kino.database.MovieRealm;

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
        List<FavoriteRealm> movie = realm.copyFromRealm(realm.where(FavoriteRealm.class).equalTo(mContext.getString(R.string.id), id).findAll());
        if (movie.size()!=0) {
            callback.onSuccess(movie.get(0), Boolean.TRUE);
        } else {
            FavoriteRealm favoriteRealm = convertingMovieRealmToFavoriteRealm(
                    getMovieRealm(id, realm));
            callback.onSuccess(favoriteRealm, Boolean.FALSE);
        }
        realm.close();
    }

    @Override
    public void addMovie(FavoriteRealm movie, CallbackMovieChange callback) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm ->
                realm.insert(movie));
        callback.onSuccess(true);
        realm.close();
    }

    @Override
    public void deleteMovie(FavoriteRealm movie, CallbackMovieChange callback) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            FavoriteRealm favorite = realm1.where(FavoriteRealm.class).equalTo(mContext.getString(R.string.id), movie.getId()).findFirst();
            assert favorite != null;
            favorite.deleteFromRealm();
            callback.onSuccess(false);
        });
    }

    private MovieRealm getMovieRealm(int id, Realm realm) {
        MovieRealm movie = realm.copyFromRealm(realm.where(MovieRealm.class).equalTo(mContext.getString(R.string.id), id).findFirst());
        return movie;
    }

    private FavoriteRealm convertingMovieRealmToFavoriteRealm(MovieRealm movie) {
        FavoriteRealm favorite = new FavoriteRealm();
        favorite.setId(movie.getId());
        favorite.setVoteAverage(movie.getVoteAverage());
        favorite.setTitle(movie.getTitle());
        favorite.setPosterPath(movie.getPosterPath());
        favorite.setGenreIds(movie.getGenreIds());
        favorite.setOverview(movie.getOverview());
        favorite.setReleaseDate(movie.getReleaseDate());
        return favorite;
    }
}
