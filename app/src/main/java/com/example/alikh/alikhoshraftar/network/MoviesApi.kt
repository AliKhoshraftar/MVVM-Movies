package com.example.alikh.alikhoshraftar.network

import com.example.alikh.alikhoshraftar.model.Movie
import com.example.alikh.alikhoshraftar.model.Search
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    @GET("/")
    fun search(@Query("apiKey") apiKey: String, @Query("s") searchText: String, @Query("page") pageNumber: Int): Observable<Search>

    @GET("/")
    fun getDetail(@Query("apiKey") apiKey: String, @Query("i") movieId: String): Observable<Movie>
}