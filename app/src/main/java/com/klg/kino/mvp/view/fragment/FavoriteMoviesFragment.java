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
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.contract.FavoriteMoviesContract;
import com.klg.kino.mvp.presenter.FavoriteMoviesPresenter;
import com.klg.kino.mvp.view.activity.MovieInfoActivity;
import com.klg.kino.mvp.view.adapter.MoviesAdapter;
import com.klg.kino.mvp.view.adapter.UpdateableFragmentListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        UpdateableFragmentListener, FavoriteMoviesContract.View {
    private Boolean mFlag = Boolean.FALSE;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private List<MovieRealm> mMovies;
    private FavoriteMoviesContract.Presenter mPresenter;
    private MoviesAdapter mAdapter;

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
        mPresenter.getMovies();
        return view;
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
        mFlag = Boolean.FALSE;
    }

    @Override
    public void showError(String message) {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void update() {
        mFlag = Boolean.FALSE;
        mPresenter.updateMoviesWithFilter();
    }

    @Override
    public void showMovies(List<MovieRealm> movies) {
        mSwipeRefresh.setRefreshing(false);
        mMovies = movies;
        if (mFlag == Boolean.FALSE) {
            createMoviesAdapter();
        }
    }

    @Override
    public void showMoreMovies(List<MovieRealm> movies) {
        mMovies.addAll(movies);
        mAdapter.notifyDataSetChanged();
        mAdapter.setLoaded();
    }

    private void viewInformationAboutMovie(int id) {
        Intent intent = new Intent(mContext, MovieInfoActivity.class);
        intent.putExtra(mContext.getString(R.string.movie_id), id);
        startActivity(intent);
    }

    private void createMoviesAdapter() {
        mAdapter = new MoviesAdapter(mContext, mRecyclerView, mMovies,
                this::viewInformationAboutMovie);
        mAdapter.setOnLoadMoreListener(() -> {
            mMovies.add(null);
            mAdapter.notifyItemInserted(mMovies.size() - 1);
            mMovies.remove(mMovies.size() - 1);
            mAdapter.notifyItemRemoved(mMovies.size());
            mPresenter.loadMoreMovies();
        });
        mRecyclerView.setAdapter(mAdapter);
        mFlag = Boolean.TRUE;
    }

    private void updateViewDependencies(View view) {
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh_favorite_movie);
        mSwipeRefresh.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.recycler_view_favorite_movies);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
