package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ActorsResponse(
    val id:Int,

    @SerialName("cast")
    val actors: List<ActorDTO>
)