package com.yousufsohail.tmdb.moviedetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yousufsohail.tmdb.R
import com.yousufsohail.tmdb.common.obtainViewModel
import com.yousufsohail.tmdb.data.source.dummy.DummyContent
import com.yousufsohail.tmdb.databinding.MovieDetailBinding
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_detail.view.*

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */
class MovieDetailFragment : Fragment() {

    private lateinit var viewDataBinding: MovieDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.movie_detail, container, false)
        viewDataBinding = MovieDetailBinding.bind(view).apply {
            viewmodel = obtainViewModel()
        }
        setHasOptionsMenu(true)
        return view
    }

    private fun obtainViewModel(): MovieDetailViewModel = obtainViewModel(MovieDetailViewModel::class.java)


    override fun onResume() {
        super.onResume()
        viewDataBinding.viewmodel?.start(arguments!!.getInt(ARG_ITEM_ID))
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
