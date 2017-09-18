package com.klg.kino.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.klg.kino.R;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.view.adapter.callback.CallBackMovieObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieRealm> mMovies;
    private CallBackMovieObject mCallBackMovie;
    private Context mContext;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public SearchAdapter(Context context, List<MovieRealm> movies, CallBackMovieObject callBack) {
        mContext = context;
        mMovies = movies;
        mCallBackMovie = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        return mMovies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_view_movie_search, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieRealm movie = mMovies.get(position);
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.mTextViewMovieName.setText(movie.getTitle());
        movieViewHolder.mTextViewMovieDataRelease.setText(movie.getReleaseDate());
        Picasso.with(mContext).load(mContext.getString(R.string.base_image_url) + movie.getPosterPath())
                .into(movieViewHolder.mImageViewMoviePoster);
        movieViewHolder.mCardViewMovie.setOnClickListener(view ->
                mCallBackMovie.clickMovie(mMovies.get(position)));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewMovieName;
        private TextView mTextViewMovieDataRelease;
        private ImageView mImageViewMoviePoster;
        private CardView mCardViewMovie;

        MovieViewHolder(View itemView) {
            super(itemView);
            mTextViewMovieName = itemView.findViewById(R.id.text_view_name_movie);
            mTextViewMovieDataRelease = itemView.findViewById(R.id.text_view_data_release_movie);
            mImageViewMoviePoster = itemView.findViewById(R.id.image_view_movie);
            mCardViewMovie = itemView.findViewById(R.id.card_view_movie);
        }
    }
}
