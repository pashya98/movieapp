package com.prashant.movieapp.ui.base

/**
 * Created by Prashant Mhetre on 18/4/19.
 */
interface BaseNavigator {
    fun showMessage(message:String)
    fun showLoading()
    fun hideLoading()
}