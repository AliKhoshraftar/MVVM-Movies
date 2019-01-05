package com.example.alikh.alikhoshraftar.model

data class Search(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String,
    val Error: String
)