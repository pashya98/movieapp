package com.prashant.movieapp.ui.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.prashant.movieapp.BuildConfig

import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.BaseItem
import com.prashant.movieapp.data.model.MovieInfo
import com.prashant.movieapp.data.model.TVShowInfo
import com.prashant.movieapp.util.Constant
import kotlinx.android.synthetic.main.constrain_demo_layout.*


class DetailFragment : Fragment(),DetailNevigator {

    private lateinit var mShowDetails: BaseItem
   // private lateinit var mDetailsViewModel:DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mShowDetails = it.getSerializable(Constant.ID) as BaseItem

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_detail, container, false)
       // return inflater.inflate(R.layout.constrain_demo_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load( BuildConfig.IMAGE_BASE_URL+mShowDetails.poster_path)
            .error(R.mipmap.ic_launcher)
            .into(iv_poster)

        mShowDetails.let { it ->
            when(it) {
                is MovieInfo ->{
                    tv_title.text=it.title
                    tv_overview.text=it.overview
                    tv_release_date.text=it.release_date
                    rating_bar.rating=(it.vote_average/2f)
                }
                is TVShowInfo -> {
                    tv_title.text=it.name
                    tv_overview.text=it.overview
                    tv_release_date.text=it.release_date
                    rating_bar.rating=(it.vote_average/2f)
                }
                else -> {it}
            }

        }
       //
//        activity?.let {
//            mDetailsViewModel = ViewModelProviders.of(it).get(DetailsViewModel::class.java)
//        }
//        mDetailsViewModel.getMoviesDetails(this,id).observe(this, Observer<MovieDetail> {
//            it?.let {
//              Glide.with(this)
//            .load( BuildConfig.IMAGE_BASE_URL+mShowDetails.poster_path)
//            .into(iv_poster)
//        tv_title.text=mShowDetails.name
//            }
//        })

    }

    companion object {
        fun newInstance(item: BaseItem): DetailFragment {
            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(Constant.ID, item)
                }
                println("ID: $id")
            }
            return fragment;
        }
    }

    override fun showMessage(message: String) {
       Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
