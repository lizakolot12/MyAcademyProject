package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MoviesResult(

    @SerialName("results")
    val movies: List<MovieBaseJson>
)