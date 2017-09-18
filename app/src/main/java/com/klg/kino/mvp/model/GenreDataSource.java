package com.klg.kino.mvp.model;

import android.content.Context;

import com.klg.kino.R;
import com.klg.kino.api.model.Genre;
import com.klg.kino.api.model.GenresAnswer;
import com.klg.kino.database.GenreRealm;
import com.klg.kino.retrofit.RetrofitProvider;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by sergejkozin on 9/1/17.
 */

public class GenreDataSource implements GenreBaseDataSource {
    private RetrofitProvider mRetrofitProvider;
    private Subscription mSubscription;


    public GenreDataSource(Context context) {
        mRetrofitProvider = new RetrofitProvider(
                context.getString(R.string.base_url),
                context.getString(R.string.token));
    }

    @Override
    public boolean isGenresTableIsNotEmpty() {
        Realm realm = Realm.getDefaultInstance();
        return !realm.where(GenreRealm.class).findAll().isEmpty();
    }

    @Override
    public void addGenres(CallbackGenres callbackGenres) {
        mSubscription = mRetrofitProvider.getAllGenres()
                .map(this::getGenresFromGenresAnswer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> {
                            addGenresInLocalBase(s);
                            callbackGenres.onSuccess();
                        }, throwable -> callbackGenres.onFailure());
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null)
            mSubscription.unsubscribe();
    }

    @Override
    public List<GenreRealm> getGenres() {
        List<GenreRealm> genres;
        try (Realm realm = Realm.getDefaultInstance()) {
           genres = realm.copyFromRealm(realm.where(GenreRealm.class).findAll());
        } catch (RealmException ignored) {
            genres = new ArrayList<>();
        }
        return genres;
    }

    @Override
    public void updateGenres(List<GenreRealm> genres) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm ->
                realm.insertOrUpdate(genres));
        realm.close();
    }

    private void addGenresInLocalBase(List<Genre> genres) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm -> {
            GenreRealm baseGenre;
            for (Genre genre : genres) {
                baseGenre = new GenreRealm();
                baseGenre.setId(genre.getId());
                baseGenre.setName(genre.getName());
                baseGenre.setIsChecked(false);
                realm.insert(baseGenre);
            }
        });
        realm.close();
    }

    private List<Genre> getGenresFromGenresAnswer(GenresAnswer answer) {
        return answer.getGenres();
    }

}
