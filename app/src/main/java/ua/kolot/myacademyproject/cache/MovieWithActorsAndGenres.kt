package ua.kolot.myacademyproject.cache

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithActorsAndGenres(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "actorId",
        associateBy = Junction(MovieActorCrossRefEntity::class)
    )
    val actors: List<ActorEntity>?,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreCrossRefEntity::class),
    )
    val genres: List<GenreEntity>
)