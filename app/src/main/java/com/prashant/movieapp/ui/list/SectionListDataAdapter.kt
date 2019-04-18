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
import com.prashant.movieapp.data.model.BaseItem
import com.prashant.movieapp.data.model.MovieInfo

class SectionListDataAdapter<T : List<BaseItem>>(private val mContext: Context,private val mCallBack:OnClickShow, private val mItemsList: T):
    RecyclerView.Adapter<SectionListDataAdapter<T>.SingleItemRowHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item_movie, null)
        return SingleItemRowHolder(view)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, position: Int) {
        println("Class Name: "+mItemsList[position].javaClass.simpleName)
        if(mItemsList[position].name!=null){
            holder.tvTitle.text = mItemsList[position].name
        }else{
            holder.tvTitle.text = mItemsList[position].title
        }

            Glide
                .with(mContext)
                .load( BuildConfig.IMAGE_BASE_URL+mItemsList[position].poster_path)
                .into(holder.itemImage!!)
        holder.itemView.setOnClickListener({
            if(mItemsList[position].name!=null) {
                mCallBack.onClickShow(mItemsList[position].id,2)
            }else{
                mCallBack.onClickShow(mItemsList[position].id,1)
            }
        })
    }

    override fun getItemCount(): Int {
        return mItemsList.size
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
         var tvTitle: TextView
         var itemImage: ImageView? = null

        init {
            this.tvTitle = view.findViewById(R.id.tvTitle)
            this.itemImage = view.findViewById(R.id.ivItemImage)
        }
    }

    interface OnClickShow{
        fun onClickShow(id:Int,type:Int)
    }

}
