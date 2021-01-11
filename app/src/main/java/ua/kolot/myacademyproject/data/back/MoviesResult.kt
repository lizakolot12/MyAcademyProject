package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.kolot.myacademyproject.data.back.MovieBaseJson

@Serializable
class MoviesResult(
    @SerialName("results")
    val movies: List<MovieBaseJson>
)