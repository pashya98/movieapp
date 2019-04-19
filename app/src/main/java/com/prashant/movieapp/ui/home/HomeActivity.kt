package com.prashant.movieapp.ui.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.prashant.movieapp.R
import com.prashant.movieapp.data.model.BaseItem
import com.prashant.movieapp.ui.details.DetailFragment
import com.prashant.movieapp.ui.list.MovieListFragment

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), MovieListFragment.OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.title=getString(R.string.title_activity_home)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MovieListFragment())
            .commit()
    }

    override fun onFragmentInteraction(item: BaseItem) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title=getString(R.string.show_details)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DetailFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title=getString(R.string.title_activity_home)
    }

}
