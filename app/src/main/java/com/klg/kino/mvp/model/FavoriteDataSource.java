package com.klg.kino.mvp.model;

import com.klg.kino.database.FavoriteRealm;
import java.util.List;
import io.realm.Realm;

/**
 * Created by sergejkozin on 9/18/17.
 */

public class FavoriteDataSource implements FavoriteBaseDataSource {

    @Override
    public void getMovies(CallbackMovies callback) {
        Realm realm = Realm.getDefaultInstance();
        List<FavoriteRealm> movies = realm.copyFromRealm(realm.where(FavoriteRealm.class).findAll());
        realm.close();
        callback.onSuccess(movies);
    }
}
