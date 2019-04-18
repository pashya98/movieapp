package com.prashant.movieapp.ui.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.prashant.movieapp.R
import com.prashant.movieapp.ui.details.DetailFragment
import com.prashant.movieapp.ui.list.MovieListFragment

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), MovieListFragment.OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MovieListFragment(),"list")
            .commit()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onFragmentInteraction(id: Int,type:Int) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DetailFragment.newInstance(id,type),"details")
            .addToBackStack(null)
            .commit()
    }

}
