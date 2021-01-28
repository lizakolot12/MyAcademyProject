package ua.kolot.myacademyproject.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Contract.TABLE_NAME_GENRES)
class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = Contract.COLUMN_NAME_ID_GENRE)
    val id: Long,

    @ColumnInfo(name = Contract.COLUMN_NAME_NAME)
    val name: String)