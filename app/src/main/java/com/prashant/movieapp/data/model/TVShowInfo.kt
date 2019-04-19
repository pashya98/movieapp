package com.prashant.movieapp.data.model

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
class TVShowInfo (  var vote_count: Int,
                  //  var id: Int,
                    var video: Boolean,
                    var vote_average: Float,
                  //  var name: String,
                    var popularity:Double,
                   // var poster_path:String,
                    var original_language:String,
                    var original_name:String,
                    var genre_ids:Array<Int>,
                    var backdrop_path:String,
                    var adult: Boolean,
                    var overview:String,
                    var release_date:String,
                    var origin_country:Array<String>,
                    var first_air_date:String):BaseItem()

