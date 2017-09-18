package com.klg.kino.database;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergejkozin on 9/17/17.
 */

public class FavoriteRealm extends RealmObject {
    private Integer mId;
    private Double mVoteAverage;
    private String mTitle;
    private String mPosterPath;
    private RealmList<IntegerRealm> mGenreIds = null;
    private String mOverview;
    private String mReleaseDate;

    public FavoriteRealm() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public RealmList<IntegerRealm> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(RealmList<IntegerRealm> genreIds) {
        mGenreIds = genreIds;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }
}
