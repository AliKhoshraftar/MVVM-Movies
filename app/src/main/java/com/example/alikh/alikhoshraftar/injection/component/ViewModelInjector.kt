package com.example.alikh.alikhoshraftar.injection.component

import com.example.alikh.alikhoshraftar.injection.module.NetworkModule
import com.example.alikh.alikhoshraftar.ui.movie.MovieDetailFragmentViewModel
import com.example.alikh.alikhoshraftar.ui.movie.MovieSearchViewModel
import com.example.alikh.alikhoshraftar.ui.movie.MovieViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(movieSearchViewModel: MovieSearchViewModel)
    fun inject(movieViewModel: MovieViewModel)
    fun inject(movieViewModel: MovieDetailFragmentViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}