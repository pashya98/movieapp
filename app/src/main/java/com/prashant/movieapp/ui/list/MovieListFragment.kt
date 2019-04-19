package com.prashant.movieapp.ui.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.BaseItem
import com.prashant.movieapp.data.model.HeaderSection
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment(), MovieListNavigator, SectionListDataAdapter.OnClickShow {

    private lateinit var mHomeViewModel: MovieListViewModel
    private lateinit var mSectionalAdapter: SectionAdapter
    private var mFragmentInetraction: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSectionalAdapter = SectionAdapter(activity as Context, this, ArrayList<HeaderSection>())
        rv_sectional.adapter = mSectionalAdapter
        activity?.let {
            mHomeViewModel = ViewModelProviders.of(it).get(MovieListViewModel::class.java)
        }
        getMovies()
    }

    fun getMovies() {
        mHomeViewModel.getMoviesList(this).observe(this, object : Observer<List<HeaderSection>> {
            override fun onChanged(sectionList: List<HeaderSection>?) {
                if (sectionList != null) {
                    mSectionalAdapter.updateSection(sectionList)
                }
            }
        })
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
         progress_bar.visibility=View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility=View.GONE
    }

    override fun onClickShow(item: BaseItem) {
        mFragmentInetraction?.onFragmentInteraction(item)
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
        fun onFragmentInteraction(item: BaseItem)
    }

}
