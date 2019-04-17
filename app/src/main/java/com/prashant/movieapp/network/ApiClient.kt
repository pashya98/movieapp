package com.prashant.movieapp.network

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
    //  https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=rECvXat5JIMkG59TNS8J4Ri2ZVHh8pYH
    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/movie/"
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }


}