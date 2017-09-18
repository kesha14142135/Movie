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
import com.klg.kino.mvp.contract.PopularMoviesContract;
import com.klg.kino.mvp.presenter.PopularMoviesPresenter;
import com.klg.kino.mvp.view.activity.HomeActivity;
import com.klg.kino.mvp.view.activity.MovieInfoActivity;
import com.klg.kino.mvp.view.adapter.MoviesAdapter;
import com.klg.kino.mvp.view.adapter.UpdateableFragmentListener;

import java.util.ArrayList;
import java.util.List;

import static com.klg.kino.R.string.haveLocalData;

public class PopularMoviesFragment extends Fragment implements UpdateableFragmentListener,
        SwipeRefreshLayout.OnRefreshListener, PopularMoviesContract.View {
    private Boolean mFlag = Boolean.FALSE;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private List<MovieRealm> mMovies;
    private PopularMoviesContract.Presenter mPresenter;
    private MoviesAdapter mAdapter;

    public static PopularMoviesFragment newInstance() {
        return new PopularMoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        updateViewDependencies(view);
        mMovies = new ArrayList<>();
        mPresenter = new PopularMoviesPresenter();
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
        //Add SnackBar
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
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh_popular_movie);
        mSwipeRefresh.setOnRefreshListener(this);
        mRecyclerView = view.findViewById(R.id.recycler_view_popular_movies);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
