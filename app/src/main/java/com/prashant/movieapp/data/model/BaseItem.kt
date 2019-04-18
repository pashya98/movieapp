package com.prashant.movieapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Prashant Mhetre on 17/4/19.
 */
open class BaseItem(
               var id: Int,
               @SerializedName("name")
               var name: String,
               @SerializedName("title")
               var title: String,
               var poster_path:String) {
   constructor():this(0,"","","")
}