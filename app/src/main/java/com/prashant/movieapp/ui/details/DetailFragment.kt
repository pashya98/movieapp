package com.prashant.movieapp.ui.details


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.prashant.movieapp.R
import com.prashant.movieapp.util.Constant


class DetailFragment : Fragment() {

    private var showId: Int = 0
    private var showType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            showId = it.getInt(Constant.ID)
            showType = it.getInt(Constant.TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(id: Int, type: Int):DetailFragment {
            val fragment= DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constant.ID, id)
                    putInt(Constant.TYPE, type)
                }
                println("ID: $id")
            }
            return fragment;
        }
    }
}
