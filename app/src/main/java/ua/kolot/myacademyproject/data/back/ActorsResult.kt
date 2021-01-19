package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.kolot.myacademyproject.data.back.ActorJson

@Serializable
class ActorsResult(
    val id:Int,

    @SerialName("cast")
    val actors: List<ActorJson>
)