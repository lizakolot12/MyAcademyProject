package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["idMovie", "idGenre"],
    tableName = "movie_genre",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["idMovie"],
            childColumns = ["idMovie"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["idGenre"],
            childColumns = ["idGenre"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class MovieGenreCrossRef(

    @ColumnInfo(name = "idMovie")
    val idMovie: Int,

    @ColumnInfo(name = "idGenre")
    val idGenre: Int
)