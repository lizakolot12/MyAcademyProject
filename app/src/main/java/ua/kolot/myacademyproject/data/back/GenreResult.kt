package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.kolot.myacademyproject.data.back.GenreJson

@Serializable
class GenreResult(
    @SerialName("genres")
    val genres: List<GenreJson>
)