package com.klg.kino.mvp.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.klg.kino.mvp.view.fragment.PopularMoviesFragment;
import com.klg.kino.R;
import com.klg.kino.mvp.view.fragment.FavoriteMoviesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class PagerAdapterTabHome extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTabs;

    public PagerAdapterTabHome(FragmentManager fm, Context context) {
        super(fm);
        mFragments = addFragmentsToSwipe();
        mTabs = addTabToSwipe(context);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof UpdateableFragmentListener) {
            ((UpdateableFragmentListener) object).update();
        }
        return super.getItemPosition(object);
    }

    public void update() {
        notifyDataSetChanged();
    }

    private List<Fragment> addFragmentsToSwipe() {
        List<Fragment> fragment = new ArrayList<>();
        fragment.add(FavoriteMoviesFragment.newInstance());
        fragment.add(PopularMoviesFragment.newInstance());
        return fragment;
    }

    private List<String> addTabToSwipe(Context context) {
        List<String> list = new ArrayList<>();
        list.add(context.getResources().getString(R.string.favorite_films));
        list.add(context.getResources().getString(R.string.popular_films));
        return list;
    }
}
