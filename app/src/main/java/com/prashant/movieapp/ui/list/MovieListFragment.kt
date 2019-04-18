package com.prashant.movieapp.ui.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.HeaderSection
import com.prashant.recycleassignment.home.SectionAdapter
import com.prashant.recycleassignment.home.SectionListDataAdapter

class MovieListFragment : Fragment(),MovieListNavigator, SectionListDataAdapter.OnClickShow {

    private lateinit var mHomeViewModel: MovieListViewModel
    private lateinit var mSectionalAdapter: SectionAdapter
    private lateinit var mSectionalRecycleView:RecyclerView
    private lateinit var mProgressBar:ProgressBar
    private var mFragmentInetraction: OnFragmentInteractionListener?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSectionalRecycleView=view.findViewById(R.id.rv_sectional)
        mProgressBar=view.findViewById(R.id.progress_bar)
        mSectionalAdapter = SectionAdapter(activity as Context,this, ArrayList<HeaderSection>())
        mSectionalRecycleView.adapter = mSectionalAdapter
        activity?.let {
            mHomeViewModel = ViewModelProviders.of(it).get(MovieListViewModel::class.java)
        }
        getMovies()
    }

    fun getMovies(){
        mHomeViewModel.getMoviesList(this).observe(this, object : Observer<List<HeaderSection>> {
            override fun onChanged(sectionList: List<HeaderSection>?) {
                if(sectionList!=null){
                    mSectionalAdapter.updateSection(sectionList)
                }
            }
        })
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
       // mProgressBar.visibility=View.VISIBLE
    }

    override fun hideLoading() {
      //  mProgressBar.visibility=View.GONE
    }

    override fun onClickShow(id: Int, type: Int) {
        mFragmentInetraction?.onFragmentInteraction(id,type)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mFragmentInetraction = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mFragmentInetraction = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(id:Int,type:Int)
    }

}
