package com.klg.kino.mvp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.klg.kino.R;

public class MovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        Intent intent = getIntent();
        int haveLocalData = intent.getIntExtra(getString(R.string.movie_id), 0);
    }
}
