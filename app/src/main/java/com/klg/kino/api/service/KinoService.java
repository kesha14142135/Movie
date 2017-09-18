package com.klg.kino.api.service;

import com.klg.kino.api.model.MovieInfo;
import com.klg.kino.api.model.MoviesAnswer;
import com.klg.kino.api.model.GenresAnswer;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sergejkozin on 8/29/17.
 */

public interface KinoService {

    @GET("genre/movie/list")
    Observable<GenresAnswer> getAllGenres(
            @Query("api_key") String apiKey);

    @GET("search/movie")
    Observable<MoviesAnswer> searchMovie(
            @Query("query") String query,
            @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Observable<MovieInfo> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<MoviesAnswer> getMovies(
            @Query("sort_by") String sortBy,
            @Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<MoviesAnswer> getMovies(
            @Query("sort_by") String sortBy,
            @Query("page") int page,
            @Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<MoviesAnswer> getMovies(
            @Query("sort_by") String sortBy,
            @Query("page") int page,
            @Query("with_genres") String ganres,
            @Query("api_key") String apiKey);
}
