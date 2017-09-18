package com.klg.kino.mvp.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.klg.kino.R;
import com.klg.kino.database.GenreRealm;
import com.klg.kino.mvp.view.adapter.GenresAdapter;
import com.klg.kino.mvp.view.adapter.UpdateableFragmentListener;
import com.klg.kino.mvp.view.callback.CallBackGenreInActivity;

import java.util.List;


/**
 * Created by sergej on 31.03.17.
 */

public class GenresDialogFragment extends DialogFragment implements
        View.OnClickListener {
    private Context mContext;
    private GenresAdapter mGenresAdapter;
    private CallBackGenreInActivity callBackGenreInActivity;
    private static List<GenreRealm> sGenres;

    public static GenresDialogFragment newInstance(List<GenreRealm> genres) {
        sGenres = genres;
        return new GenresDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof CallBackGenreInActivity) {
            callBackGenreInActivity = (CallBackGenreInActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_genre, container, false);
        updateViewDependencies(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_apply_filter: {
                callBackGenreInActivity.genreFilterChanged(mGenresAdapter.getChecked());
                getDialog().dismiss();
                break;
            }
            case R.id.button_cancel_filter: {
                getDialog().dismiss();
                break;
            }
        }
    }

    private void updateViewDependencies(View view) {
        view.findViewById(R.id.button_apply_filter).setOnClickListener(this);
        view.findViewById(R.id.button_cancel_filter).setOnClickListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_genres);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        mGenresAdapter = new GenresAdapter(mContext, sGenres);
        recyclerView.setAdapter(mGenresAdapter);
    }


}

