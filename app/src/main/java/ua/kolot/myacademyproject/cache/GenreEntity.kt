package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "idGenre")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String)