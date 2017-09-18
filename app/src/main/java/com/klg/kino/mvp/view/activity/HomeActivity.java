package com.klg.kino.mvp.view.activity;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.klg.kino.R;
import com.klg.kino.database.GenreRealm;
import com.klg.kino.mvp.contract.HomeContract;
import com.klg.kino.mvp.presenter.HomePresenter;
import com.klg.kino.mvp.view.fragment.SearchFragment;
import com.klg.kino.mvp.view.callback.CallBackGenreInActivity;
import com.klg.kino.mvp.view.fragment.GenresDialogFragment;
import com.klg.kino.mvp.view.fragment.MoviesFragment;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements CallBackGenreInActivity, HomeContract.View {

    private Fragment mTabFragment;
    private FragmentTransaction mTransaction;
    private DialogFragment mDialogFragmentGenre;
    private HomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //TODO When the user first time. And genres are not stored in the database. Hide filter button
        //Intent intent = getIntent();
        //Boolean haveLocalData = intent.getBooleanExtra(getString(R.string.haveLocalData), false);
        mPresenter = new HomePresenter();
        mPresenter.attachView(this);
        updateViewDependencies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_filter: {
                mPresenter.getGenres();
                return true;
            }
            case R.id.item_search: {
                mTabFragment = SearchFragment.newInstance();
                initialisationTransaction(mTabFragment);
                return true;
            }
            case R.id.item_home: {
                mTabFragment = MoviesFragment.newInstance();
                initialisationTransaction(mTabFragment);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void genreFilterChanged(List<GenreRealm> genres) {
        mPresenter.updateGenres(genres);
        updateFragmentMovie();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(R.id.drawer_layout_base_home), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showGenres(List<GenreRealm> genres) {
        mDialogFragmentGenre = GenresDialogFragment.newInstance(genres);
        mDialogFragmentGenre.show(getSupportFragmentManager(), getString(R.string.dialog_genre));
    }

    private void updateViewDependencies() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.movies);
        setSupportActionBar(toolbar);
        mTabFragment = MoviesFragment.newInstance();
        initialisationTransaction(mTabFragment);
    }

    private void initialisationTransaction(Fragment fragment) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.frame_layout_base_container, mTabFragment, getString(R.string.tag_movie));
        mTransaction.commit();
    }

    private void updateFragmentMovie() {
        if (mTabFragment instanceof MoviesFragment) {
            MoviesFragment movie = (MoviesFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_movie));
            if (movie != null)
                movie.updateWithFilter();
        }
    }
}
