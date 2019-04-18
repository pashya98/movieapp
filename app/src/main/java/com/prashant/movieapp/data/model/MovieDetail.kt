package com.prashant.movieapp.data.model

/**
 * Created by Prashant Mhetre on 18/4/19.
 */
data class MovieDetail(
                        var adult:Boolean,
                        var backdrop_path:String,
                        var belongs_to_collection:String,
                        var budget:Int,
                        var genres:ArrayList<Genres>,
                        var homepage:String,
                        var id:Int,
                        var imdb_id:String,
                        var original_language:String,
                        var original_title:String,
                        var overview:String,
                        var popularity:Double,
                        var poster_path:String,
                        var production_companies:List<ProductionCompanies>,
                        var release_date:String,
                        var revenue:Int,
                        var runtime:Int,
                        var spoken_languages:List<Language>,
                        var status:String,
                        var tagline:String,
                        var title:String,
                        var video:Boolean,
                        var vote_average:Double,
                        var vote_count:Int)

data class Genres(var id:Int,
                  var name:String)

data class ProductionCompanies(var id: Int,
                               var logo_path:String,
                               var name:String,
                               var original_country:String)

data class Language(var iso_639_1:String,
                    var name:String)




