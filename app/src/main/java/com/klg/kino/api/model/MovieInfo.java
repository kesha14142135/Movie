package com.klg.kino.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class MovieInfo {
    @SerializedName("adult")
    @Expose
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    @Expose
    private String mBackdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    private Object mBelongsToCollection;
    @SerializedName("budget")
    @Expose
    private Integer mBudget;
    @SerializedName("genres")
    @Expose
    private List<Genre> mGenres = null;
    @SerializedName("homepage")
    @Expose
    private String mHomepage;
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("imdb_id")
    @Expose
    private String mImdbId;
    @SerializedName("original_language")
    @Expose
    private String mOriginalLanguage;
    @SerializedName("original_title")
    @Expose
    private String mOriginalTitle;
    @SerializedName("overview")
    @Expose
    private String mOverview;
    @SerializedName("popularity")
    @Expose
    private Double mPopularity;
    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;
    @SerializedName("production_companies")
    @Expose
    private List<Company> mProductionCompanies = null;
    @SerializedName("production_countries")
    @Expose
    private List<Country> mProductionCountries = null;
    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;
    @SerializedName("revenue")
    @Expose
    private Integer mRevenue;
    @SerializedName("runtime")
    @Expose
    private Integer mRuntime;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> mSpokenLanguages = null;
    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("tagline")
    @Expose
    private String mTagline;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("video")
    @Expose
    private Boolean mVideo;
    @SerializedName("vote_average")
    @Expose
    private Double mVoteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer mVoteCount;

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Object getBelongsToCollection() {
        return mBelongsToCollection;
    }

    public void setBelongsToCollection(Object belongsToCollection) {
        mBelongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return mBudget;
    }

    public void setBudget(Integer budget) {
        mBudget = budget;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public Integer getmId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public List<Company> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public List<Country> getmProductionCountries() {
        return mProductionCountries;
    }

    public void setmProductionCountries(List<Country> productionCountries) {
        mProductionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return mRevenue;
    }

    public void setRevenue(Integer revenue) {
        mRevenue = revenue;
    }

    public Integer getRuntime() {
        return mRuntime;
    }

    public void setRuntime(Integer runtime) {
        mRuntime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return mSpokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        mSpokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setTagline(String tagline) {
        mTagline = tagline;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
        mVideo = video;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Integer voteCount) {
        mVoteCount = voteCount;
    }


}
