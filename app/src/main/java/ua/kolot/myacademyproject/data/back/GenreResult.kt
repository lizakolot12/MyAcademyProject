package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GenreResult(
    @SerialName("genres")
    val genres: List<GenreJson>
)