package com.klg.kino.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.klg.kino.R;
import com.klg.kino.mvp.contract.SplashContract;
import com.klg.kino.mvp.presenter.SplashPresenter;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPresenter = new SplashPresenter();
        mPresenter.attachView(this);
        mPresenter.isFirstOpen();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void transitionHomeScreen(boolean haveLocalData) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(getString(R.string.haveLocalData), haveLocalData);
        startActivity(intent);
        finish();
    }
}
