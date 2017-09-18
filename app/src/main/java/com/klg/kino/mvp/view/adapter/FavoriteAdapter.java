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
import com.klg.kino.database.FavoriteRealm;
import com.klg.kino.database.MovieRealm;
import com.klg.kino.mvp.view.adapter.callback.CallBackMovieId;
import com.klg.kino.mvp.view.adapter.callback.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FavoriteRealm> mMovies;
    private CallBackMovieId mCallBackMovie;
    private Context mContext;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public FavoriteAdapter(Context context, List<FavoriteRealm> movies, CallBackMovieId callBack) {
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.card_view_movie, parent, false);
            return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            FavoriteRealm movie = mMovies.get(position);
            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
            movieViewHolder.mTextViewMovieName.setText(movie.getTitle());
            movieViewHolder.mTextViewMovieDescription.setText(movie.getOverview());
            Picasso.with(mContext).load(mContext.getString(R.string.base_image_url) + movie.getPosterPath())
                    .into(movieViewHolder.mImageViewMoviePoster);
            movieViewHolder.mCardViewMovie.setOnClickListener(view ->
                    mCallBackMovie.clickMovie(mMovies.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
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
}
