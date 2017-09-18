package com.klg.kino.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klg.kino.R;
import com.klg.kino.database.GenreRealm;

import java.util.List;

/**
 * Created by sergejkozin on 9/15/17.
 */

public class GenresAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GenreRealm> mGenres;
    private Context mContext;

    public GenresAdapter(Context context, List<GenreRealm> genres) {
        mGenres = genres;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_radio_button, parent, false);
        return new GenresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GenresViewHolder genreHolder = (GenresViewHolder) holder;
        genreHolder.mCheckBox.setText(mGenres.get(position).getName());
        genreHolder.mCheckBox.setChecked(mGenres.get(position).getIsChecked());
        genreHolder.mCheckBox.setOnClickListener(view ->
                mGenres.get(position).setIsChecked(!mGenres.get(position).getIsChecked()));
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public List<GenreRealm> getChecked() {
        return mGenres;
    }

    private class GenresViewHolder extends RecyclerView.ViewHolder {
        private AppCompatCheckBox mCheckBox;

        GenresViewHolder(View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.check_box_genre);
        }
    }
}
