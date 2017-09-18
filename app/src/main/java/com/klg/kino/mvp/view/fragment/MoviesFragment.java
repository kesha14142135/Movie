package com.klg.kino.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klg.kino.R;
import com.klg.kino.mvp.view.adapter.*;

public class MoviesFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private PagerAdapterTabHome mAdapter;

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        updateViewDependencies(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void updateViewDependencies(View view) {
        mAdapter = new PagerAdapterTabHome(getFragmentManager(), getActivity());
        mViewPager = view.findViewById(R.id.view_pager_home);
        mViewPager.setAdapter(mAdapter);
        mTabLayout = view.findViewById(R.id.tab_layout_home);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void updateWithFilter() {
       mAdapter.update();
    }
}
