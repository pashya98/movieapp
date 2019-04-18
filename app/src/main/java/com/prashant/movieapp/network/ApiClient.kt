package com.prashant.movieapp.network

import com.prashant.movieapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
class ApiClient {
   // https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
   //https://api.themoviedb.org/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1
   // https://api.themoviedb.org/3/movie/latest?api_key=<<api_key>>&language=en-US
   // https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>&language=en-US&page=1

  //  https://api.themoviedb.org/3/tv/popular?api_key=<<api_key>>&language=en-US&page=1
    //https://api.themoviedb.org/3/tv/top_rated?api_key=<<api_key>>&language=en-US&page=1
    //https://api.themoviedb.org/3/tv/latest?api_key=<<api_key>>&language=en-US
    //https://api.themoviedb.org/3/tv/airing_today?api_key=<<api_key>>&language=en-US&page=1
    companion object {
        //val BASE_URL = "https://api.themoviedb.org/3/"
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }


}