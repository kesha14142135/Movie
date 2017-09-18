package com.klg.kino.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klg.kino.R;
import com.klg.kino.database.FavoriteRealm;
import com.klg.kino.mvp.contract.FavoriteMoviesContract;
import com.klg.kino.mvp.presenter.FavoriteMoviesPresenter;
import com.klg.kino.mvp.view.activity.MovieInfoActivity;
import com.klg.kino.mvp.view.adapter.FavoriteAdapter;
import com.klg.kino.mvp.view.adapter.UpdateableFragmentListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        UpdateableFragmentListener, FavoriteMoviesContract.View {
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private List<FavoriteRealm> mMovies;
    private FavoriteMoviesContract.Presenter mPresenter;
    private FavoriteAdapter mAdapter;

    public static FavoriteMoviesFragment newInstance() {
        return new FavoriteMoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_movies, container, false);
        updateViewDependencies(view);
        mMovies = new ArrayList<>();
        mPresenter = new FavoriteMoviesPresenter();
        mPresenter.attachView(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMovies();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        mPresenter.getMovies();
    }

    @Override
    public void showError(String message) {
        //Add SnackBar
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void update() {
        mPresenter.getMovies();
    }

    @Override
    public void showMovies(List<FavoriteRealm> movies) {
        mSwipeRefresh.setRefreshing(false);
        mMovies = movies;
        createMoviesAdapter();
    }

    private void viewInformationAboutMovie(int id) {
        Intent intent = new Intent(mContext, MovieInfoActivity.class);
        intent.putExtra(mContext.getString(R.string.movie_id), id);
        startActivity(intent);
    }

    private void createMoviesAdapter() {
        mAdapter = new FavoriteAdapter(mContext, mMovies, this::viewInformationAboutMovie);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void updateViewDependencies(View view) {
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh_favorite_movie);
        mSwipeRefresh.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.recycler_view_favorite_movies);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
