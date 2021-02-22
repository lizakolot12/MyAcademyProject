package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["movieId", "actorId"],
    tableName = "movie_actor",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["actorId"],
            childColumns = ["actorId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class MovieActorCrossRefEntity(

    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @ColumnInfo(name = "actorId")
    val actorId: Int
)