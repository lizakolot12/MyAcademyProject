package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MoviesResponse(

    @SerialName("results")
    val movies: List<MovieDTO>
)