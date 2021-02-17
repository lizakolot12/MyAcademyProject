package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["movieId", "genreId"],
    tableName = "movie_genre",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["genreId"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class MovieGenreCrossRefEntity(

    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @ColumnInfo(name = "genreId")
    val genreId: Int
)