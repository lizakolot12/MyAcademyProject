package ua.kolot.myacademyproject.data

class Movie(
    val id: Int,
    val title: String,
    val categories: String,
    val posterId: Int,
    val duration: Int,
    val rating: Float,
    val reviews: Int,
    val actors: List<Actor>
)