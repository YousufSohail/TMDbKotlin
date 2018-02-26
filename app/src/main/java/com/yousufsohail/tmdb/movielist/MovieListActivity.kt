package com.yousufsohail.tmdb.movielist

import android.app.DatePickerDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import com.yousufsohail.tmdb.R
import com.yousufsohail.tmdb.common.BaseAdapter
import com.yousufsohail.tmdb.common.obtainViewModel
import com.yousufsohail.tmdb.data.Movie
import com.yousufsohail.tmdb.databinding.ActivityMovieListBinding
import com.yousufsohail.tmdb.moviedetail.MovieDetailActivity
import com.yousufsohail.tmdb.moviedetail.MovieDetailFragment
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.movie_list.*
import java.util.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [MovieDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MovieListActivity : AppCompatActivity(), BaseAdapter.OnItemClickListener<Movie>, DatePickerDialog.OnDateSetListener {

    private lateinit var viewDataBinding: ActivityMovieListBinding
    private lateinit var adapter: BaseAdapter<Movie>

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)

        viewDataBinding.let {
            it.viewmodel = obtainViewModel()
            initAdapter()
        }

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (movie_detail_container != null) mTwoPane = true



        val layoutManager = LinearLayoutManager(this)
        movie_list.layoutManager = layoutManager

        val listScrollLister = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewDataBinding.viewmodel?.loadMore(layoutManager.itemCount, layoutManager.findLastVisibleItemPosition())
            }
        }

        movie_list.addOnScrollListener(listScrollLister)

    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewmodel?.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_filter -> {
                DatePickerDialog(
                        this,
                        this,
                        viewDataBinding.viewmodel!!.calendar.get(Calendar.YEAR),
                        viewDataBinding.viewmodel!!.calendar.get(Calendar.MONTH),
                        viewDataBinding.viewmodel!!.calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(item: Movie) {
        if (mTwoPane) {
            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(MovieDetailFragment.ARG_ITEM_ID, item.id)
                }
            }
            supportFragmentManager.beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit()
        } else {
            val intent = Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailFragment.ARG_ITEM_ID, item.id)
            }
            startActivity(intent)
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewDataBinding.viewmodel!!.setDate(year, month, dayOfMonth)
    }

    private fun initAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = BaseAdapter(R.layout.movie_list_content, this)
            movie_list.setHasFixedSize(true)
            movie_list.adapter = adapter
        } else {
            Log.w(TAG, "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun obtainViewModel(): MovieListViewModel = obtainViewModel(MovieListViewModel::class.java)

    companion object {
        private val TAG = this::class.java.simpleName

    }
}
