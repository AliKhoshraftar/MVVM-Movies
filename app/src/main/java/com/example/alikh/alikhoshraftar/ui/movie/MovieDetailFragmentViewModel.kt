package com.example.alikh.alikhoshraftar.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.view.View
import com.example.alikh.alikhoshraftar.base.BaseViewModel
import com.example.alikh.alikhoshraftar.model.Movie
import com.example.alikh.alikhoshraftar.network.MoviesApi
import com.example.alikh.alikhoshraftar.utils.constant.API_KEY
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MovieDetailFragmentViewModel : BaseViewModel() {

    @Inject
    lateinit var moviesApi: MoviesApi

    private val movie: MutableLiveData<Movie> = MutableLiveData()
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {

    }

    fun loadMovie(movieId: String) {
        subscription = moviesApi.getDetail(API_KEY, movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveMoviesListStart() }
            .doOnTerminate { onRetrieveMoviesListFinish() }
            .subscribe(
                { result -> onRetrieveMoviesListSuccess(result) },
                { err -> onRetrieveMoviesListError(err) })
    }

    private fun onRetrieveMoviesListError(err: Throwable?) {
        Timber.e(err)
    }

    private fun onRetrieveMoviesListSuccess(movie: Movie?) {
        Timber.d(movie.toString())
        this.movie.postValue(movie)
    }

    private fun onRetrieveMoviesListFinish() {
        loadingVisibility.postValue(View.GONE)
        Timber.d("finished")
    }

    private fun onRetrieveMoviesListStart() {
        loadingVisibility.postValue(View.VISIBLE)
        Timber.d("started")
    }

    fun getMovie(): MutableLiveData<Movie> {
        return movie
    }
}
