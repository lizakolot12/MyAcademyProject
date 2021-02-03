package ua.kolot.myacademyproject.cache

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithActorsAndGenres(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "idMovie",
        entityColumn = "idActor",
        associateBy = Junction(MovieActorCrossRef::class)
    )
    val actors: List<ActorEntity>?,

    @Relation(
        parentColumn = "idMovie",
        entityColumn = "idGenre",
        associateBy = Junction(MovieGenreCrossRef::class),
    )
    val genres: List<GenreEntity>
)