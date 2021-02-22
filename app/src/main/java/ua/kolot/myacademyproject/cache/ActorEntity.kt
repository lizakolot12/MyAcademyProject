package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
class ActorEntity(
    @PrimaryKey
    @ColumnInfo(name = "actorId")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "picture")
    val picture: String?
)

