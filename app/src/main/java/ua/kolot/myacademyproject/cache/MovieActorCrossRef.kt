package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["idMovie", "idActor"],
    tableName = "movie_actor",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["idMovie"],
            childColumns = ["idMovie"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["idActor"],
            childColumns = ["idActor"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class MovieActorCrossRef(

    @ColumnInfo(name = "idMovie")
    val idMovie: Int,

    @ColumnInfo(name = "idActor")
    val idActor: Int
)