package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GenreDTO(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)