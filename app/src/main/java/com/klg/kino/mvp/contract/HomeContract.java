package com.klg.kino.mvp.contract;


import com.klg.kino.database.GenreRealm;

import java.util.List;

/**
 * Created by sergejkozin on 8/22/17.
 */

public interface HomeContract {

    interface View extends BaseContract.View {

        void showGenres(List<GenreRealm> genres);
    }

    interface Presenter extends BaseContract.Presenter<HomeContract.View> {

        void getGenres();

        void updateGenres(List<GenreRealm> genres);
    }
}
