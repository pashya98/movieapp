package com.prashant.movieapp.network

import com.prashant.movieapp.data.model.CommonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
interface ApiInterface {
    @GET("top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String,
                          @Query("language") language:String,
                          @Query("page") page:Int): Single<CommonResponse>

    @GET("popular")
    fun getPoularMovies(@Query("api_key") apiKey: String,
                        @Query("language") language:String,
                        @Query("page") page:Int): Single<CommonResponse>

    @GET("now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String,
                       @Query("language") language:String,
                       @Query("page") page:Int): Single<CommonResponse>

    @GET("upcoming")
    fun getUpcomming(@Query("api_key") apiKey: String,
                     @Query("language") language:String,
                     @Query("page") page:Int): Single<CommonResponse>
}