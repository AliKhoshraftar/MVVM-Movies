package com.example.alikh.alikhoshraftar.model

data class Movie(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String,
    val totalSeasons: String

) {
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (Actors != other.Actors) return false
        if (Awards != other.Awards) return false
        if (BoxOffice != other.BoxOffice) return false
        if (Country != other.Country) return false
        if (DVD != other.DVD) return false
        if (Director != other.Director) return false
        if (Genre != other.Genre) return false
        if (Language != other.Language) return false
        if (Metascore != other.Metascore) return false
        if (Plot != other.Plot) return false
        if (Poster != other.Poster) return false
        if (Production != other.Production) return false
        if (Rated != other.Rated) return false
        if (Ratings != other.Ratings) return false
        if (Released != other.Released) return false
        if (Response != other.Response) return false
        if (Runtime != other.Runtime) return false
        if (Title != other.Title) return false
        if (Type != other.Type) return false
        if (Website != other.Website) return false
        if (Writer != other.Writer) return false
        if (Year != other.Year) return false
        if (imdbID != other.imdbID) return false
        if (imdbRating != other.imdbRating) return false
        if (imdbVotes != other.imdbVotes) return false
        if (totalSeasons != other.totalSeasons) return false

        return true
    }
}