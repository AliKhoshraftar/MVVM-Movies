package com.example.alikh.alikhoshraftar.ui.movie

import androidx.lifecycle.MutableLiveData
import com.example.alikh.alikhoshraftar.base.BaseViewModel
import com.example.alikh.alikhoshraftar.model.Movie
import com.example.alikh.alikhoshraftar.model.Rating

class MovieViewModel : BaseViewModel() {

    private val title = MutableLiveData<String>()
    private val type = MutableLiveData<String>()
    private val imdbID = MutableLiveData<String>()
    private val year = MutableLiveData<String>()
    private val poster = MutableLiveData<String>()
    private val rated = MutableLiveData<String>()
    private val released = MutableLiveData<String>()
    private val runtime = MutableLiveData<String>()
    private val genre = MutableLiveData<String>()
    private val director = MutableLiveData<String>()
    private val writer = MutableLiveData<String>()
    private val actors = MutableLiveData<String>()
    private val plot = MutableLiveData<String>()
    private val language = MutableLiveData<String>()
    private val country = MutableLiveData<String>()
    private val awards = MutableLiveData<String>()
    private val ratings = MutableLiveData<List<Rating>>()
    private val metascore = MutableLiveData<String>()
    private val imdbRating = MutableLiveData<String>()
    private val imdbVotes = MutableLiveData<String>()
    private val totalSeasons = MutableLiveData<String>()

    fun bind(movie: Movie) {
        title.value = movie.Title
        type.value = movie.Type
        imdbID.value = movie.imdbID
        year.value = movie.Year
        poster.value = movie.Poster
        rated.value = movie.Rated
        released.value = movie.Released
        runtime.value = movie.Runtime
        genre.value = movie.Genre
        director.value = movie.Director
        writer.value = movie.Writer
        actors.value = movie.Actors
        plot.value = movie.Plot
        language.value = movie.Language
        country.value = movie.Country
        awards.value = movie.Awards
        ratings.value = movie.Ratings
        metascore.value = movie.Metascore
        imdbRating.value = movie.imdbRating
        imdbVotes.value = movie.imdbVotes
        totalSeasons.value = movie.totalSeasons
    }

    fun getMovieTitle(): MutableLiveData<String> {
        return title
    }

    fun getMovieType(): MutableLiveData<String> {
        return type
    }

    fun getMovieImdbID(): MutableLiveData<String> {
        return imdbID
    }

    fun getMovieYear(): MutableLiveData<String> {
        return year
    }

    fun getMoviePoster(): MutableLiveData<String> {
        return poster
    }

    fun getMovieRated(): MutableLiveData<String> {
        return rated
    }

    fun getMovieReleased(): MutableLiveData<String> {
        return released
    }

    fun getMovieRuntime(): MutableLiveData<String> {
        return runtime
    }

    fun getMovieGenre(): MutableLiveData<String> {
        return genre
    }

    fun getMovieDirector(): MutableLiveData<String> {
        return director
    }

    fun getMovieWriter(): MutableLiveData<String> {
        return writer
    }

    fun getMovieActors(): MutableLiveData<String> {
        return actors
    }

    fun getMoviePlot(): MutableLiveData<String> {
        return plot
    }

    fun getMovieLanguage(): MutableLiveData<String> {
        return language
    }

    fun getMovieCountry(): MutableLiveData<String> {
        return country
    }

    fun getMovieAwards(): MutableLiveData<String> {
        return awards
    }

    fun getMovieRatings(): MutableLiveData<List<Rating>> {
        return ratings
    }

    fun getMovieMetascore(): MutableLiveData<String> {
        return metascore
    }

    fun getMovieImdbRating(): MutableLiveData<String> {
        return imdbRating
    }

    fun getMovieImdbVotes(): MutableLiveData<String> {
        return imdbVotes
    }

    fun getMovieTotalSeasons(): MutableLiveData<String> {
        return totalSeasons
    }

}