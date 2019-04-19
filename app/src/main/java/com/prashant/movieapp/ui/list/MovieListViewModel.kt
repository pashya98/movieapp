package com.prashant.movieapp.ui.list

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.prashant.movieapp.data.model.HeaderSection
import com.prashant.movieapp.network.ApiClient
import com.prashant.movieapp.network.ApiInterface
import com.prashant.movieapp.ui.base.BaseNavigator
import com.prashant.movieapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
class MovieListViewModel : BaseViewModel() {

    private var mCompositeDisposable: CompositeDisposable
    private var mApiService: ApiInterface
    private val mApiKey: String = "671ea541a469d6ebded23881e9323133"
    private val mLanguage: String = "en-US"
    private val mPage: Int = 1
    private lateinit var mMovieList: MutableLiveData<List<HeaderSection>>
    private lateinit var mList: ArrayList<HeaderSection>
    private lateinit var mCallback: BaseNavigator

    init {
        this.mCompositeDisposable = CompositeDisposable()
        mApiService = ApiClient.getClient()!!.create(ApiInterface::class.java)
    }

    fun getMoviesList(callback: MovieListNavigator): MutableLiveData<List<HeaderSection>> {
        mCallback = callback
        mMovieList = MutableLiveData()
        mList = ArrayList<HeaderSection>()
        getNowPlayingMovies()
        getPoularMovies()
        getTopRatedMovies()
        getUpcommingMovies()

        getLatestTVShows()
        getPoularTVShows()
        getTopRatedTVShows()
        getAiringTodayTVShows()
        return mMovieList
    }

    fun getNowPlayingMovies() {
        mCallback.showLoading()
        mCompositeDisposable.add(
            mApiService.getNowPlayingMovies(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    mCallback.hideLoading()
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Now Playing Movies", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
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

    fun getPoularMovies() {
        mCompositeDisposable.add(
            mApiService.getPoularMovies(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Poular Movies", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }

                })
        )
    }

    fun getTopRatedMovies() {
        mCompositeDisposable.add(
            mApiService.getTopRatedMovies(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Top Rated Movies", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }

                })
        )
    }

    fun getUpcommingMovies() {
        mCompositeDisposable.add(
            mApiService.getUpcommingMovies(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Upcomming Movies", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }

                })
        )
    }

    fun getLatestTVShows() {
        //mCallBack.showLoading()
        mCompositeDisposable.add(
            mApiService.getLatestTVShows(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    //                mCallBack.hideLoading()
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Latest TV Show", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        //    mCallBack.hideLoading()
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            // mCallBack.showMessage(httpException.message())
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        //  mCallBack.hideLoading()
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }

                })
        )
    }

    fun getPoularTVShows() {
        mCompositeDisposable.add(
            mApiService.getPoularTVShows(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Poular TV Show", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }

                })
        )
    }

    fun getTopRatedTVShows() {
        mCompositeDisposable.add(
            mApiService.getTopRatedTVShows(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Top Rated TV Show", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }

                })
        )
    }

    fun getAiringTodayTVShows() {
        mCompositeDisposable.add(
            mApiService.getAiringTodayTVShows(mApiKey, mLanguage, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ commonResponse ->
                    if (commonResponse?.results != null) {
                        mList.add(HeaderSection("Airing Today TV Show", commonResponse.results))
                        mMovieList.setValue(mList)
                        print("Server Data: " + commonResponse.results)
                    }
                }, { throwable ->
                    print(throwable)
                    try {
                        if (throwable is HttpException) {
                            val httpException = throwable
                            println("Http Error: " + throwable.message)
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$httpException.message()")
                        } else if (throwable is IOException) {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        } else {
                            Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$throwable.message")
                        }
                    } catch (exception: Exception) {
                        Log.e(MovieListViewModel::class.java.getSimpleName(), "Error::$exception")
                    }

                })
        )
    }

}