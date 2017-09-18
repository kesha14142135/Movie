package com.klg.kino.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.klg.kino.R;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.view.adapter.callback.CallBackMovieId;
import com.klg.kino.mvp.view.adapter.callback.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieRealm> mMovies;
    private CallBackMovieId mCallBackMovie;
    private Context mContext;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public MoviesAdapter(Context context, RecyclerView recyclerView, List<MovieRealm> movies, CallBackMovieId callBack) {
        mContext = context;
        mMovies = movies;
        mCallBackMovie = callBack;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    } else {
                        isLoading = true;
                    }
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return mMovies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.card_view_movie, parent, false);
            return new MovieViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            MovieRealm movie = mMovies.get(position);
            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
            movieViewHolder.mTextViewMovieName.setText(movie.getTitle());
            movieViewHolder.mTextViewMovieDescription.setText(movie.getOverview());
            Picasso.with(mContext).load(mContext.getString(R.string.base_image_url) + movie.getPosterPath())
                    .into(movieViewHolder.mImageViewMoviePoster);
            movieViewHolder.mCardViewMovie.setOnClickListener(view ->
                    mCallBackMovie.clickMovie(mMovies.get(position).getId()));
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewMovieName;
        private TextView mTextViewMovieDescription;
        private ImageView mImageViewMoviePoster;
        private CardView mCardViewMovie;

        MovieViewHolder(View itemView) {
            super(itemView);
            mTextViewMovieName = itemView.findViewById(R.id.text_view_name_movie);
            mTextViewMovieDescription = itemView.findViewById(R.id.text_view_description_movie);
            mImageViewMoviePoster = itemView.findViewById(R.id.image_view_movie);
            mCardViewMovie = itemView.findViewById(R.id.card_view_movie);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar_recycler_view);
        }
    }

}
