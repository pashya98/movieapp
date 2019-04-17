package com.prashant.movieapp.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.prashant.movieapp.data.model.HeaderSection
import com.prashant.movieapp.network.ApiClient
import com.prashant.movieapp.network.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
class HomeViewModel: ViewModel() {

    private  var mCompositeDisposable: CompositeDisposable
    lateinit var mMovieList: MutableLiveData<List<HeaderSection>>
    private var mApiService : ApiInterface
    private val mApiKey:String="671ea541a469d6ebded23881e9323133"
    private val mLanguage:String="en-US"
    private val mPage:Int=1
    lateinit var mList:ArrayList<HeaderSection>

    init {
        this.mCompositeDisposable = CompositeDisposable()
        mApiService = ApiClient.getClient()!!.create(ApiInterface::class.java)
    }

    fun getMoviesList(): MutableLiveData<List<HeaderSection>>{
        mMovieList=MutableLiveData()
        mList=ArrayList<HeaderSection>()
        getNowPlayingMovies()
        getPoularMovies()
        getTopRatedMovies()
        getUpcomming()
        return mMovieList
    }

    fun getNowPlayingMovies() {
        //mCallBack.showLoading()
        mCompositeDisposable.add(mApiService.getNowPlayingMovies(mApiKey,mLanguage,mPage)
            .subscribeOn(Schedulers.io())
            .observeOn( AndroidSchedulers.mainThread())
            .subscribe({ commonResponse ->
//                mCallBack.hideLoading()
                if (commonResponse != null && commonResponse.results != null) {
                    mList.add(HeaderSection("Now Playing", commonResponse.results))
                    mMovieList.setValue(mList)
                    print("Server Data: "+commonResponse.results)
                }
            }, { throwable ->
                print(throwable)
                try {
                //    mCallBack.hideLoading()
                    if (throwable is HttpException) {
                        val httpException = throwable
                        println("Http Error: " + throwable.message)
                       // mCallBack.showMessage(httpException.message())
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                    } else if (throwable is IOException) {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    } else {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    }
                } catch (exception: Exception) {
                  //  mCallBack.hideLoading()
                    Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$exception")
                }

            })
        )
    }

    fun getPoularMovies() {
        mCompositeDisposable.add(mApiService.getPoularMovies(mApiKey,mLanguage,mPage)
            .subscribeOn(Schedulers.io())
            .observeOn( AndroidSchedulers.mainThread())
            .subscribe({ commonResponse ->
                if (commonResponse != null && commonResponse.results != null) {
                    mList.add(HeaderSection("Poular", commonResponse.results))
                    mMovieList.setValue(mList)
                    print("Server Data: "+commonResponse.results)
                }
            }, { throwable ->
                print(throwable)
                try {
                    if (throwable is HttpException) {
                        val httpException = throwable
                        println("Http Error: " + throwable.message)
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                    } else if (throwable is IOException) {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    } else {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    }
                } catch (exception: Exception) {
                    Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$exception")
                }

            })
        )
    }

    fun getTopRatedMovies() {
        mCompositeDisposable.add(mApiService.getTopRatedMovies(mApiKey,mLanguage,mPage)
            .subscribeOn(Schedulers.io())
            .observeOn( AndroidSchedulers.mainThread())
            .subscribe({ commonResponse ->
                if (commonResponse != null && commonResponse.results != null) {
                    mList.add(HeaderSection("Top Rated", commonResponse.results))
                    mMovieList.setValue(mList)
                    print("Server Data: "+commonResponse.results)
                }
            }, { throwable ->
                print(throwable)
                try {
                    if (throwable is HttpException) {
                        val httpException = throwable
                        println("Http Error: " + throwable.message)
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                    } else if (throwable is IOException) {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    } else {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    }
                } catch (exception: Exception) {
                    Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$exception")
                }

            })
        )
    }

    fun getUpcomming() {
        mCompositeDisposable.add(mApiService.getUpcomming(mApiKey,mLanguage,mPage)
            .subscribeOn(Schedulers.io())
            .observeOn( AndroidSchedulers.mainThread())
            .subscribe({ commonResponse ->
                if (commonResponse != null && commonResponse.results != null) {
                    mList.add(HeaderSection("Upcomming", commonResponse.results))
                    mMovieList.setValue(mList)
                    print("Server Data: "+commonResponse.results)
                }
            }, { throwable ->
                print(throwable)
                try {
                    if (throwable is HttpException) {
                        val httpException = throwable
                        println("Http Error: " + throwable.message)
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                    } else if (throwable is IOException) {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    } else {
                        Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                    }
                } catch (exception: Exception) {
                    Log.e(HomeViewModel::class.java.getSimpleName(), "Error::$exception")
                }

            })
        )
    }

}