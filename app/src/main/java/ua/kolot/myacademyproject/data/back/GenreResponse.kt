package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GenreResponse(
    @SerialName("genres")
    val genres: List<GenreDTO>
)