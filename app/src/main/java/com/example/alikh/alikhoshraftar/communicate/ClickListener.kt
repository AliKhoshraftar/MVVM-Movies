package com.example.alikh.alikhoshraftar.communicate

import com.example.alikh.alikhoshraftar.model.Movie

interface ClickListener {
    interface MoviesListClickEvent {
        fun loadMovie(movie: Movie)
    }
}