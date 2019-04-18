package com.prashant.movieapp.data.model

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
data class MovieInfo(
    var vote_count: Int,
 //   var id: Int,
    var video: Boolean,
    var vote_average: Double,
//    var title: String,
    var popularity:Double,
//    var poster_path:String,
    var original_language:String,
    var original_title:String,
    var genre_ids:Array<Int>,
    var backdrop_path:String,
    var adult: Boolean,
    var overview:String,
    var release_date:String
): BaseItem()

class demo1<T : List<BaseItem>>(private val list: List<BaseItem>)

internal class demo3<T>(mylist: T) {
    private val list: T

    init {
        list = mylist
    }
}
