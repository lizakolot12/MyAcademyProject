package ua.kolot.myacademyproject.cache

import androidx.room.*

@Entity(
    primaryKeys = [Contract.COLUMN_NAME_ID_MOVIE, Contract.COLUMN_NAME_ID_ACTOR],
    tableName = Contract.TABLE_NAME_MOVIE_ACTORS,
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = [Contract.COLUMN_NAME_ID_MOVIE],
            childColumns = [Contract.COLUMN_NAME_ID_MOVIE],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = [Contract.COLUMN_NAME_ID_ACTOR],
            childColumns = [Contract.COLUMN_NAME_ID_ACTOR],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class MovieActorCrossRef(

    @ColumnInfo(name = Contract.COLUMN_NAME_ID_MOVIE)
    val idMovie: Long,

    @ColumnInfo(name = Contract.COLUMN_NAME_ID_ACTOR)
    val idActor: Long
)


@Entity(
    primaryKeys = [Contract.COLUMN_NAME_ID_MOVIE, Contract.COLUMN_NAME_ID_GENRE],
    tableName = Contract.TABLE_NAME_MOVIE_GENRE,
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = [Contract.COLUMN_NAME_ID_MOVIE],
            childColumns = [Contract.COLUMN_NAME_ID_MOVIE],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = [Contract.COLUMN_NAME_ID_GENRE],
            childColumns = [Contract.COLUMN_NAME_ID_GENRE],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class MovieGenreCrossRef(

    @ColumnInfo(name = Contract.COLUMN_NAME_ID_MOVIE)
    val idMovie: Long,

    @ColumnInfo(name = Contract.COLUMN_NAME_ID_GENRE)
    val idGenre: Long
)

data class MovieWithActorsAndGenres(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = Contract.COLUMN_NAME_ID_MOVIE,
        entityColumn = Contract.COLUMN_NAME_ID_ACTOR,
        associateBy = Junction(MovieActorCrossRef::class)
    )
    val actors: List<ActorEntity>?,

    @Relation(
        parentColumn = Contract.COLUMN_NAME_ID_MOVIE,
        entityColumn = Contract.COLUMN_NAME_ID_GENRE,
        associateBy = Junction(MovieGenreCrossRef::class),
    )
    val genres: List<GenreEntity>
)