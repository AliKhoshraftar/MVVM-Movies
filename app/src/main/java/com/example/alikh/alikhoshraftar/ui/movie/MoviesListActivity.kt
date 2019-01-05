package com.example.alikh.alikhoshraftar.ui.movie

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.alikh.alikhoshraftar.R
import com.example.alikh.alikhoshraftar.communicate.ClickListener
import com.example.alikh.alikhoshraftar.databinding.ActivityMoviesListBinding
import com.example.alikh.alikhoshraftar.model.Movie
import com.example.alikh.alikhoshraftar.utils.EndlessRecyclerViewScrollListener
import com.example.alikh.alikhoshraftar.utils.constant.EMPTY_TEXT
import com.example.alikh.alikhoshraftar.utils.extension.hideKeyboard
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity(), ClickListener.MoviesListClickEvent {

    private lateinit var binding: ActivityMoviesListBinding
    private lateinit var viewModel: MovieSearchViewModel
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private lateinit var movieDetailFragment: MovieDetailFragment
    private lateinit var mFragmentManager: androidx.fragment.app.FragmentManager
    private lateinit var transaction: androidx.fragment.app.FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()

        mFragmentManager = supportFragmentManager

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_list)
        viewModel = ViewModelProviders.of(this).get(MovieSearchViewModel::class.java)
        scrollListener =
                object : EndlessRecyclerViewScrollListener(binding.moviesList.layoutManager as androidx.recyclerview.widget.LinearLayoutManager) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: androidx.recyclerview.widget.RecyclerView) {
                        viewModel.loadMoreMovies(page + 1)
                    }
                }

        binding.moviesList.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)
        viewModel.getMovieEvent().observe(this, Observer { handleMovieEvent(it!!) })
        viewModel.getSearchError().observe(this, Observer { handleSearchError(it!!) })
        viewModel.setClickEventListener(this)
        binding.viewModel = viewModel

    }

    private fun handleSearchError(error: String) {
        showError(error)
    }

    private fun hideError() {
        movies_list_error_image.visibility = GONE
        movies_list_error_text.visibility = GONE
        movies_list_error_text.text = EMPTY_TEXT
    }

    private fun showError(error: String) {
        movies_list_error_text.visibility = VISIBLE
        movies_list_error_image.visibility = VISIBLE
        movies_list_error_text.text = error
    }

    private fun handleMovieEvent(movieEvent: MovieEvent) {
        when (movieEvent) {
            MovieEvent.ANIMATE_RECYCLER -> {
                movies_list.scheduleLayoutAnimation()
                movies_list_search.clearFocus()
                hideKeyboard()
            }
            MovieEvent.SEARCH_TEXT_CHANGED -> {
                hideError()
                movies_list_search_error.visibility = GONE
            }
            MovieEvent.SEARCH_MIN_CHAR -> {
                movies_list_search_error.visibility = VISIBLE
                movies_list_search_error.text = getString(R.string.min_search_required)
            }
            MovieEvent.SEARCH_SAME_TEXT -> {
                movies_list_search_error.visibility = VISIBLE
                movies_list_search_error.text = getString(R.string.search_same_result)
            }
        }
    }

    override fun onBackPressed() {
        if (mFragmentManager.backStackEntryCount > 0) {
            mFragmentManager.popBackStackImmediate()
            movie_detail_fragment_container.visibility = GONE
        } else {
            super.onBackPressed()
        }
    }

    private fun showMovieDetail(movieId: String) {
        movie_detail_fragment_container.visibility = VISIBLE
        transaction = mFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.fragment_enter,
            R.anim.fragment_exit,
            R.anim.fragment_pop_enter,
            R.anim.fragment_pop_exit
        )
        movieDetailFragment = MovieDetailFragment.newInstance(movieId)
        transaction.addToBackStack(movieDetailFragment.tag)
        transaction.replace(R.id.movie_detail_fragment_container, movieDetailFragment)
        transaction.commit()
    }

    override fun loadMovie(movie: Movie) {
        showMovieDetail(movie.imdbID)
    }

}
