package com.klg.kino.mvp.view.callback;

import com.klg.kino.database.GenreRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public interface CallBackGenreInActivity {
    void genreFilterChanged(List<GenreRealm> genres);
}
