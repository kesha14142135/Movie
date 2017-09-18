package com.klg.kino.mvp.contract;

import android.content.Context;

/**
 * Created by sergejkozin on 7/2/17.
 */

public interface BaseContract {

    interface View {

        Context getContext();

        void showError(String message);
    }

    interface Presenter<V extends View> {

        void attachView(V view);

        void detachView();
    }

}
