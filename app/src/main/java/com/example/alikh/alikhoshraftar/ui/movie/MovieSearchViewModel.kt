package com.example.alikh.alikhoshraftar.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.view.View
import com.example.alikh.alikhoshraftar.base.BaseViewModel
import com.example.alikh.alikhoshraftar.communicate.ClickListener
import com.example.alikh.alikhoshraftar.model.Search
import com.example.alikh.alikhoshraftar.network.MoviesApi
import com.example.alikh.alikhoshraftar.utils.*
import com.example.alikh.alikhoshraftar.utils.constant.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MovieSearchViewModel : BaseViewModel() {

    @Inject
    lateinit var moviesApi: MoviesApi

    val movieListAdapter: MovieListAdapter = MovieListAdapter()
    private var myTimer: Timer? = null
    private var lastSearchText: String = DEFUALT_SEARCH_TEXT
    private var currentSearchText: String = DEFUALT_SEARCH_TEXT

    private val movieEvent = SingleLiveEvent<MovieEvent>()
    private val resultError: MutableLiveData<String> = MutableLiveData()

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        movieListAdapter.setHasStableIds(true)
        loadMovies(lastSearchText, MIN_PAGE_NUMBER)
    }

    fun setClickEventListener(clickEvent: ClickListener.MoviesListClickEvent) {
        movieListAdapter.setClickListener(clickEvent)
    }

    fun getMovieEvent(): LiveData<MovieEvent> {
        return movieEvent
    }

    fun getSearchError(): LiveData<String> {
        return resultError
    }

    fun loadMovies(searchText: String, pageNumber: Int) {
        subscription = moviesApi.search(API_KEY, searchText, pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveMoviesListStart() }
            .doOnTerminate { onRetrieveMoviesListFinish() }
            .subscribe(
                { result -> onRetrieveMoviesListSuccess(result) },
                { err -> onRetrieveMoviesListError(err) })
    }

    fun loadMoreMovies(pageNumber: Int) {
        loadMovies(lastSearchText, pageNumber)
    }

    private fun onRetrieveMoviesListStart() {
        loadingVisibility.postValue(View.VISIBLE)
        Timber.d("started")
    }

    private fun onRetrieveMoviesListFinish() {
        loadingVisibility.postValue(View.GONE)
        Timber.d("finished")
    }

    private fun onRetrieveMoviesListSuccess(search: Search) {
        Timber.d(search.toString())
        if (search.Response == TRUE) {
            if (search.Search.isNotEmpty()) {
                if (!searchTextsEquality()) {
                    clearAdapter()
                    lastSearchText = currentSearchText
                }
                movieListAdapter.updateMoviesList(search.Search)
                movieEvent.value = MovieEvent.ANIMATE_RECYCLER
            }
        } else {
            if (!searchTextsEquality()) {
                clearAdapter()
                notifyAdapter()
                lastSearchText = currentSearchText
                resultError.postValue(search.Error)
            } else {

            }
        }
    }

    private fun searchTextsEquality(): Boolean {
        return currentSearchText == lastSearchText
    }

    private fun onRetrieveMoviesListError(err: Throwable) {
        Timber.e(err)
        resultError.postValue(err.message)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun onSearchTextChanged(text: CharSequence) {
        if (text.isNotEmpty()) {
            currentSearchText = text.toString()
            movieEvent.value = MovieEvent.SEARCH_TEXT_CHANGED
            if (myTimer != null) {
                myTimer!!.cancel()
            }
            myTimer = Timer()
            myTimer!!.schedule(object : TimerTask() {
                override fun run() {
                    if (text.length > MIN_SEARCH_CHAR) {
                        if (lastSearchText != text.toString()) {
                            loadMovies(text.toString(), 1)
                        } else {
                            movieEvent.postValue(MovieEvent.SEARCH_SAME_TEXT)
                        }
                    } else {
                        movieEvent.postValue(MovieEvent.SEARCH_MIN_CHAR)
                        cancelTimer()
                    }
                }
            }, 1000)
        } else {
            cancelTimer()
        }
    }

    private fun clearAdapter() {
        movieListAdapter.clearList()
    }

    private fun notifyAdapter() {
        movieListAdapter.notifyDataSetChanged()
    }

    private fun cancelTimer() {
        if (myTimer != null) {
            myTimer!!.cancel()
        }
    }
}