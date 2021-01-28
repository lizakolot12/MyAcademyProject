package ua.kolot.myacademyproject.cache

import androidx.room.*

@Dao
interface GenreDao {
    @Query("SELECT * FROM " + Contract.TABLE_NAME_GENRES)
    fun getAll(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(genres: List<GenreEntity>)
}