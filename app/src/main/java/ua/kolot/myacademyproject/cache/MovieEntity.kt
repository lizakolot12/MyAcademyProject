package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Contract.TABLE_NAME_MOVIES)
class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = Contract.COLUMN_NAME_ID_MOVIE)
    val id: Long,

    @ColumnInfo(name = Contract.COLUMN_NAME_TITLE)
    val title: String,

    @ColumnInfo(name = Contract.COLUMN_NAME_OVERVIEW)
    val overview: String,

    @ColumnInfo(name = Contract.COLUMN_NAME_POSTER)
    val poster: String,

    @ColumnInfo(name = Contract.COLUMN_NAME_BACKDROP)
    val backdrop: String,

    @ColumnInfo(name = Contract.COLUMN_NAME_RATINGS)
    val ratings: Float,

    @ColumnInfo(name = Contract.COLUMN_NAME_RATING_NUMBER)
    val ratingNumber: Int,

    @ColumnInfo(name = Contract.COLUMN_NAME_MIN_AGE)
    val minimumAge: Int,
)