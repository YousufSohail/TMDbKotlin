package com.yousufsohail.tmdb.moviedetail

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.yousufsohail.tmdb.R
import com.yousufsohail.tmdb.common.obtainViewModel
import com.yousufsohail.tmdb.movielist.MovieListActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * An activity representing a single Movie detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [MovieListActivity].
 */
class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(MovieDetailFragment.ARG_ITEM_ID,
                            intent.getIntExtra(MovieDetailFragment.ARG_ITEM_ID, 0))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, MovieListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
