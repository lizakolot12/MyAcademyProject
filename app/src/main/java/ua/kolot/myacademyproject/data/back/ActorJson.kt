package ua.kolot.myacademyproject.data.back

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorJson(
    val id: Int,
    val name: String,

    @SerialName("profile_path")
    val profilePath: String?
)