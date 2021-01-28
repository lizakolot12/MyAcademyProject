package ua.kolot.myacademyproject.cache

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM " + Contract.TABLE_NAME_MOVIES)
    fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)

}