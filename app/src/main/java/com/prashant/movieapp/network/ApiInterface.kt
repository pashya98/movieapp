package com.prashant.movieapp.network

import com.prashant.movieapp.data.model.MovieDetail
import com.prashant.movieapp.data.model.MovieResponse
import com.prashant.movieapp.data.model.TVShowResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
interface ApiInterface {
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String,
                          @Query("language") language:String,
                          @Query("page") page:Int): Single<MovieResponse>

    @GET("movie/popular")
    fun getPoularMovies(@Query("api_key") apiKey: String,
                        @Query("language") language:String,
                        @Query("page") page:Int): Single<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String,
                       @Query("language") language:String,
                       @Query("page") page:Int): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcommingMovies(@Query("api_key") apiKey: String,
                     @Query("language") language:String,
                     @Query("page") page:Int): Single<MovieResponse>

    @GET("tv/top_rated")
    fun getTopRatedTVShows(@Query("api_key") apiKey: String,
                          @Query("language") language:String,
                          @Query("page") page:Int): Single<TVShowResponse>

    @GET("tv/popular")
    fun getPoularTVShows(@Query("api_key") apiKey: String,
                        @Query("language") language:String,
                        @Query("page") page:Int): Single<TVShowResponse>

    @GET("tv/latest")
    fun getLatestTVShows(@Query("api_key") apiKey: String,
                            @Query("language") language:String,
                            @Query("page") page:Int): Single<TVShowResponse>

    @GET("tv/airing_today")
    fun getAiringTodayTVShows(@Query("api_key") apiKey: String,
                     @Query("language") language:String,
                     @Query("page") page:Int): Single<TVShowResponse>

    @GET("tv/{id}")
    fun getTVShowsDetails(
        @Path("id") id:Int,
        @Query("api_key") apiKey: String,
        @Query("language") language:String): Single<MovieDetail>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
                     @Path("movie_id") id:Int,
                     @Query("api_key") apiKey: String,
                     @Query("language") language:String): Single<MovieDetail>
}