package com.prashant.recycleassignment.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.HeaderSection
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper




class SectionAdapter(var mContext: Context,var mCallBack:SectionListDataAdapter.OnClickShow,
                     var mSectionList: List<HeaderSection>) :
    RecyclerView.Adapter<SectionAdapter.ItemRowHolder>() {

    fun updateSection(timeDetailsList:List<HeaderSection>){
        mSectionList=timeDetailsList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item_section_list, null)
        return ItemRowHolder(v)
    }

    override fun onBindViewHolder(itemRowHolder: ItemRowHolder, i: Int) {

        val sectionName = mSectionList[i].headerTitle
        val singleSectionItems = mSectionList[i].allSectionItem

        itemRowHolder.itemTitle.setText(sectionName)
        val itemListDataAdapter = SectionListDataAdapter(mContext, mCallBack, singleSectionItems)
        itemRowHolder.recycler_view_list.adapter = itemListDataAdapter

    }

    override fun getItemCount(): Int {
        return mSectionList.size
    }

    inner class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        var itemTitle: TextView
        var recycler_view_list: RecyclerView

        init {
            itemTitle = view.findViewById(R.id.itemTitle)
            recycler_view_list = view.findViewById(R.id.recycler_view_list)
//            val snapHelper = LinearSnapHelper()
//            snapHelper.attachToRecyclerView(recycler_view_list)
        }
    }

}





//class SectionAdapter(val mContext: Context, val dataList: ArrayList<SectionDataModel>?) :
//    RecyclerView.Adapter<SectionAdapter.ItemRowHolder>() {
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemRowHolder {
//        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item_section_list, null)
//        return ItemRowHolder(v)
//    }
//
//    override fun onBindViewHolder(itemRowHolder: ItemRowHolder, i: Int) {
//
//        val sectionName = dataList!![i].headerTitle
//
//        val singleSectionItems = dataList[i].allItemsInSection
//
//        itemRowHolder.itemTitle.setText(sectionName)
//
//        val itemListDataAdapter = SectionListDataAdapter(mContext, singleSectionItems)
//        itemRowHolder.recycler_view_list.adapter = itemListDataAdapter
//
//
////        itemRowHolder.btnMore.setOnClickListener(object : View.OnClickListener() {
////            fun onClick(v: View) {
////
////
////                Toast.makeText(v.getContext(), "click event on more, $sectionName", Toast.LENGTH_SHORT).show()
////
////
////            }
////        })
//
//
//        /* Glide.with(mContext)
//                .load(feedItem.getImageURL())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .centerCrop()
//                .error(R.drawable.bg)
//                .into(feedListRowHolder.thumbView);*/
//    }
//
//    override fun getItemCount(): Int {
//        return dataList?.size?: 0
//    }
//
//    inner class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//         var itemTitle: TextView
//         var recycler_view_list: RecyclerView
////       var btnMore: Button
//
//
//        init {
//            this.itemTitle = view.findViewById(R.id.itemTitle)
//            this.recycler_view_list = view.findViewById(R.id.recycler_view_list)
//          //  this.btnMore = view.findViewById(R.id.btnMore) as Button
//        }
//
//    }
//
//}