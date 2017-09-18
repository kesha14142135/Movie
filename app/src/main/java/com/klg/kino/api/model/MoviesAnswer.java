package com.klg.kino.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sergejkozin on 9/14/17.
 */

public class MoviesAnswer {
    @SerializedName("page")
    @Expose
    private Integer mPage;
    @SerializedName("total_results")
    @Expose
    private Integer mTotalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer mTotalPages;
    @SerializedName("results")
    @Expose
    private List<Movie> mMovies = null;

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        mPage = page;
    }

    public Integer getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Integer totalResults) {
        mTotalResults = totalResults;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        mTotalPages = totalPages;
    }

    public List<Movie> getMovie() {
        return mMovies;
    }

    public void setFilms(List<Movie> results) {
        mMovies = results;
    }
}
