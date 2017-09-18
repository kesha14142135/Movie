package com.klg.kino.mvp.model;

import com.klg.kino.database.GenreRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/1/17.
 */

public interface GenreBaseDataSource {

    boolean isGenresTableIsNotEmpty();

    void addGenres(CallbackGenres callbackGenres);

    void unsubscribe();

    List<GenreRealm> getGenres();

    void updateGenres(List<GenreRealm> genres);

    interface CallbackGenres {
        void onSuccess();

        void onFailure();
    }
}
