package com.klg.kino.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.klg.kino.R;
import com.klg.kino.database.FavoriteRealm;
import com.klg.kino.mvp.contract.FavoriteMoviesContract;
import com.klg.kino.mvp.contract.MovieInfoContract;
import com.klg.kino.mvp.presenter.MovieInfoPresenter;
import com.squareup.picasso.Picasso;

public class MovieInfoActivity extends AppCompatActivity implements MovieInfoContract.View, View.OnClickListener {

    private TextView mTextViewTitle;
    private TextView mTextViewOverview;
    private TextView mTextViewReleaseDate;
    private TextView mTextViewVoteAverage;
    private TextView mTextViewVoteCount;
    private TextView mTextViewGenres;
    private ImageView mImageViewPosterBase;
    private ImageView mImageViewPoster;
    private Button mButtonAddMovie;
    private Button mButtonDeleteMovie;
    private MovieInfoContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        updateViewDependencies();
        Intent intent = getIntent();
        int id = intent.getIntExtra(getString(R.string.movie_id), 0);
        mPresenter = new MovieInfoPresenter();
        mPresenter.attachView(this);
        mPresenter.getMovieById(id);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showMovie(FavoriteRealm movie, boolean isInFavorite) {
        mButtonAddMovie.setEnabled(!isInFavorite);
        mButtonDeleteMovie.setEnabled(isInFavorite);
        mTextViewTitle.setText(movie.getTitle());
        mTextViewOverview.setText(movie.getOverview());
        mTextViewReleaseDate.setText(movie.getReleaseDate());
        mTextViewVoteAverage.setText(movie.getVoteAverage().toString());
        mTextViewGenres.setText("Genre");
        Picasso.with(this).load(getString(R.string.base_image_url) + movie.getPosterPath())
                .into(mImageViewPoster);
        Picasso.with(this).load(getString(R.string.base_image_url) + movie.getPosterPath())
                .into(mImageViewPosterBase);
    }

    @Override
    public void showChange(boolean isInFavorite) {
        mButtonAddMovie.setEnabled(!isInFavorite);
        mButtonDeleteMovie.setEnabled(isInFavorite);
    }

    private void updateViewDependencies() {
        mTextViewTitle = (TextView) findViewById(R.id.text_view_title_movie);
        mTextViewOverview = (TextView) findViewById(R.id.text_view_overview);
        mTextViewReleaseDate = (TextView) findViewById(R.id.text_view_movie_date_release);
        mTextViewVoteAverage = (TextView) findViewById(R.id.text_view_vote_average);
        mTextViewVoteCount = (TextView) findViewById(R.id.text_view_vote_count);
        mTextViewGenres = (TextView) findViewById(R.id.text_view_genres);
        mImageViewPoster = (ImageView) findViewById(R.id.image_movie_poster);
        mImageViewPosterBase = (ImageView) findViewById(R.id.image_movie_poster_base);
        mButtonAddMovie = (Button) findViewById(R.id.button_add_movie);
        mButtonAddMovie.setOnClickListener(this);
        mButtonDeleteMovie = (Button) findViewById(R.id.button_delete_movie);
        mButtonDeleteMovie.setOnClickListener(this);
        (findViewById(R.id.button_back_pressed_movie)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_movie: {
                mPresenter.addMovieInFavorites();
                break;
            }
            case R.id.button_delete_movie: {
                mPresenter.deleteMovieInFavorites();
                break;
            }
            case R.id.button_back_pressed_movie: {
                onBackPressed();
                break;
            }
        }
    }
}
