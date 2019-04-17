package com.prashant.recycleassignment.home

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.prashant.movieapp.BuildConfig
import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.MovieInfo

class SectionListDataAdapter(private val mContext: Context, private val mItemsList: List<MovieInfo>?) :
    RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item_movie, null)
        return SingleItemRowHolder(view)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, position: Int) {

        holder.tvTitle.text = mItemsList!![position].title
            Glide
                .with(mContext)
                .load( BuildConfig.IMAGE_BASE_URL+mItemsList[position].poster_path)
                .into(holder.itemImage!!)

    }

    override fun getItemCount(): Int {
        return mItemsList?.size ?: 0
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
         var tvTitle: TextView
         var itemImage: ImageView? = null

        init {
            this.tvTitle = view.findViewById(R.id.tvTitle)
            this.itemImage = view.findViewById(R.id.ivItemImage)
        }
    }

}
