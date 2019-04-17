package com.prashant.movieapp.ui.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.HeaderSection
import com.prashant.movieapp.ui.home.HomeViewModel
import com.prashant.recycleassignment.home.SectionAdapter

class MovieListFragment : Fragment() {

    lateinit var mSectionalRecycleView:RecyclerView
    private lateinit var mHomeViewModel:HomeViewModel
    private lateinit var mSectionalAdapter: SectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSectionalRecycleView=view.findViewById(R.id.rv_sectional)
        mSectionalAdapter = SectionAdapter(activity as Context, ArrayList<HeaderSection>())
        mSectionalRecycleView.adapter = mSectionalAdapter
        activity?.let {
            mHomeViewModel = ViewModelProviders.of(it).get(HomeViewModel::class.java)
        }
        getMovies()
    }

    fun getMovies(){
        mHomeViewModel.getMoviesList().observe(this, object : Observer<List<HeaderSection>> {
            override fun onChanged(sectionList: List<HeaderSection>?) {
                if(mSectionalAdapter!=null && sectionList!=null){
                    mSectionalAdapter.updateSection(sectionList)
                }
            }
        })
    }


}
