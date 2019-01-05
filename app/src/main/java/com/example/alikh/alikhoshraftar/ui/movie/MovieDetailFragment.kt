package com.example.alikh.alikhoshraftar.ui.movie

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.alikh.alikhoshraftar.R
import com.example.alikh.alikhoshraftar.databinding.MovieDetailFragmentBinding
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MovieDetailFragment : androidx.fragment.app.Fragment(), LifecycleOwner {

    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var activity: Activity
    private lateinit var mContext: Context
    private lateinit var movieViewModel: MovieViewModel

    companion object {
        fun newInstance() = MovieDetailFragment()
        fun newInstance(movieId: String): MovieDetailFragment {
            val movieDetailFragment = MovieDetailFragment()
            val args = Bundle()
            args.putString("movie_id", movieId)
            movieDetailFragment.arguments = args
            return movieDetailFragment
        }
    }

    private lateinit var viewModel: MovieDetailFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, null, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieDetailFragmentViewModel::class.java)
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        binding.viewModel = viewModel

        observeMovie()

        val movieId: String? = arguments!!.getString(getString(R.string.movie_detail_fragment_arg))
        viewModel.loadMovie(movieId!!)
    }

    private fun observeMovie() {
        viewModel.getMovie()
            .observe(this, Observer { movie ->
                movieViewModel.bind(movie!!)
                binding.movieViewModel = movieViewModel
                visibleTitles()
            })
    }

    private fun visibleTitles() {
        movie_detail_actors_title.visibility = VISIBLE
        movie_detail_awards_title.visibility = VISIBLE
        movie_detail_summary_title.visibility = VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        activity = context as Activity
    }
}
