package com.prashant.movieapp.ui.details

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.prashant.movieapp.data.model.HeaderSection
import com.prashant.movieapp.data.model.MovieDetail
import com.prashant.movieapp.network.ApiClient
import com.prashant.movieapp.network.ApiInterface
import com.prashant.movieapp.ui.base.BaseNavigator
import com.prashant.movieapp.ui.base.BaseViewModel
import com.prashant.movieapp.ui.list.MovieListNavigator
import com.prashant.movieapp.ui.list.MovieListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

/**
 * Created by Prashant Mhetre on 19/4/19.
 */
class DetailsViewModel:BaseViewModel() {

    private var mCompositeDisposable: CompositeDisposable
    private var mApiService: ApiInterface
    private val mApiKey: String = "671ea541a469d6ebded23881e9323133"
    private val mLanguage: String = "en-US"
    private lateinit var mMovieDetail: MutableLiveData<MovieDetail>
    private lateinit var mCallback: BaseNavigator

    init {
        this.mCompositeDisposable = CompositeDisposable()
        mApiService = ApiClient.getClient()!!.create(ApiInterface::class.java)
    }

    fun getMoviesDetails(callback: DetailNevigator,id: Int): MutableLiveData<MovieDetail> {
        mCallback = callback
        mMovieDetail = MutableLiveData()
        getMoviesDetails(id)
        return mMovieDetail
    }

    fun getMoviesDetails(id: Int) {
        mCallback.showLoading()
        mCompositeDisposable.add(
            mApiService.getMovieDetails(id, mApiKey, mLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showDetailsResponse ->
                    mCallback.hideLoading()
                    if (showDetailsResponse!= null) {

                        mMovieDetail.setValue(showDetailsResponse)
                        print("Show Details: " + showDetailsResponse)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        mCallback.hideLoading()
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            mCallback.showMessage(httpException.message())
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            mCallback.showMessage(throwable.message!!)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            mCallback.showMessage(throwable.message!!)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        mCallback.hideLoading()
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }
                })
        )
    }
}