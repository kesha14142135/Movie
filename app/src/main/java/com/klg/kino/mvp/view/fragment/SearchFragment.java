package com.klg.kino.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.klg.kino.R;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.contract.SearchMoviesContract;
import com.klg.kino.mvp.presenter.SearchMoviesPresenter;
import com.klg.kino.mvp.view.activity.MovieInfoActivity;
import com.klg.kino.mvp.view.adapter.SearchAdapter;

import java.util.List;

public class SearchFragment extends Fragment implements SearchMoviesContract.View {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private SearchMoviesContract.Presenter mPresenter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        updateViewDependencies(view);
        mPresenter = new SearchMoviesPresenter();
        mPresenter.attachView(this);
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
    public void showError(String message) {

    }

    @Override
    public void showMovies(List<MovieRealm> genres) {
        SearchAdapter adapter = new SearchAdapter(mContext, genres, this::viewInformationAboutMovie);
        mRecyclerView.setAdapter(adapter);
    }

    private void viewInformationAboutMovie(MovieRealm i) {
        mPresenter.addMovie(i);
    }

    @Override
    public void transitionMovieInfoScreen(Integer id) {
        Intent intent = new Intent(mContext, MovieInfoActivity.class);
        intent.putExtra(mContext.getString(R.string.movie_id), id);
        startActivity(intent);
    }

    private void updateViewDependencies(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_search_movies);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        SearchView searchView = view.findViewById(R.id.search_view_movies);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.getMovies(newText);
                return false;
            }
        });
    }
}
