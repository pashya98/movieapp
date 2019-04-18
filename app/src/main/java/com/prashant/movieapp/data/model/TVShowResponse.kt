package com.prashant.movieapp.data.model

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
data class TVShowResponse(
                         var page:Int,
                         var total_results:Int,
                         var total_pages: Int,
                         var results: List<TVShowInfo>
)

