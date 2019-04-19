package com.prashant.movieapp.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.prashant.movieapp.BuildConfig
import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.BaseItem
import com.prashant.movieapp.data.model.MovieInfo
import com.prashant.movieapp.data.model.TVShowInfo
import kotlinx.android.synthetic.main.row_item_movie.view.*

class SectionListDataAdapter<T : List<BaseItem>>(
    private val mContext: Context,
    private val mCallBack: OnClickShow,
    private val mItemsList: T) : RecyclerView.Adapter<SectionListDataAdapter<T>.SingleItemRowHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item_movie, null)
        return SingleItemRowHolder(view)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, position: Int) {
        when(mItemsList[position]){
            is MovieInfo -> {
                holder.itemView.tvTitle.text = mItemsList[position].title
            }
            is TVShowInfo -> {
                holder.itemView.tvTitle.text = mItemsList[position].name
            }
        }

        Glide
            .with(mContext)
            .load(BuildConfig.IMAGE_BASE_URL + mItemsList[position].poster_path)
            .error(R.mipmap.ic_launcher)
            .into(holder.itemView.ivItemImage)

        holder.itemView.setOnClickListener({
                mCallBack.onClickShow(mItemsList[position])
        })
    }

    override fun getItemCount(): Int {
        return mItemsList.size
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnClickShow {
        fun onClickShow(item: BaseItem)
    }

}
