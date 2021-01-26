package ua.kolot.myacademyproject.data

class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val ratingNumber: Int,
    val minimumAge: Int,
    val genres: List<Genre>,
    var actors: List<Actor>?
)