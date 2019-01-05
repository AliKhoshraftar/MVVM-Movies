package com.example.alikh.alikhoshraftar.ui.movie

import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.alikh.alikhoshraftar.R
import com.example.alikh.alikhoshraftar.communicate.ClickListener
import com.example.alikh.alikhoshraftar.databinding.MovieListItemBinding
import com.example.alikh.alikhoshraftar.model.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*


class MovieListAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movieList: ArrayList<Movie> = ArrayList()
    private var lastPosition = -1
    private lateinit var clickEvent: ClickListener.MoviesListClickEvent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val binding: MovieListItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.setIsRecyclable(true)
        holder.bind(movieList[position])
        holder.itemView.movies_list_item_container.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "" + position, Toast.LENGTH_SHORT).show()
            clickEvent.loadMovie(movieList[position])
        }
        setAnimation(holder.itemView, position)
    }

    override fun getItemId(position: Int): Long {
        val movie: Movie = movieList[position]
        return movie.hashCode().toLong()
    }

    fun setClickListener(clickEvent: ClickListener.MoviesListClickEvent) {
        this.clickEvent = clickEvent
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMoviesList(moviesList: List<Movie>) {
        this.movieList.addAll(moviesList)
        notifyDataSetChanged()
    }

    fun clearList() {
        movieList.clear()
    }

    class ViewHolder(private val binding: MovieListItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieViewModel()
        fun bind(movie: Movie) {
            viewModel.bind(movie)
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.fade_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }
}