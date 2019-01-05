package com.example.alikh.alikhoshraftar.base

import androidx.lifecycle.ViewModel
import com.example.alikh.alikhoshraftar.injection.component.DaggerViewModelInjector
import com.example.alikh.alikhoshraftar.injection.component.ViewModelInjector
import com.example.alikh.alikhoshraftar.injection.module.NetworkModule
import com.example.alikh.alikhoshraftar.ui.movie.MovieDetailFragmentViewModel
import com.example.alikh.alikhoshraftar.ui.movie.MovieSearchViewModel
import com.example.alikh.alikhoshraftar.ui.movie.MovieViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MovieSearchViewModel -> injector.inject(this)
            is MovieViewModel -> injector.inject(this)
            is MovieDetailFragmentViewModel -> injector.inject(this)
        }
    }
}