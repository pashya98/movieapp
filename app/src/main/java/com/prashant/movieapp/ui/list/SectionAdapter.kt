package com.prashant.movieapp.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.HeaderSection
import kotlinx.android.synthetic.main.row_item_section_list.view.*

class SectionAdapter(var mContext: Context, var mCallBack: SectionListDataAdapter.OnClickShow,
                     var mSectionList: List<HeaderSection>) : RecyclerView.Adapter<SectionAdapter.ItemRowHolder>() {

    fun updateSection(timeDetailsList: List<HeaderSection>) {
        mSectionList = timeDetailsList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemRowHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_item_section_list, null)
        return ItemRowHolder(v)
    }

    override fun onBindViewHolder(itemRowHolder: ItemRowHolder, i: Int) {
        itemRowHolder.itemView.tv_section_title.text=mSectionList[i].headerTitle
        val itemListDataAdapter = SectionListDataAdapter(mContext, mCallBack, mSectionList[i].allSectionItem)
        itemRowHolder.itemView.recycler_view_list.adapter = itemListDataAdapter
    }

    override fun getItemCount(): Int {
        return mSectionList.size
    }

    class ItemRowHolder(view: View) : RecyclerView.ViewHolder(view)
}
