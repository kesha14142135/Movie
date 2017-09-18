package com.klg.kino.mvp.contract;

/**
 * Created by sergejkozin on 8/22/17.
 */

public interface SplashContract {

    interface View extends BaseContract.View {

        void transitionHomeScreen(boolean haveLocalData);

    }

    interface Presenter extends BaseContract.Presenter<SplashContract.View> {

        void isFirstOpen();
    }
}
