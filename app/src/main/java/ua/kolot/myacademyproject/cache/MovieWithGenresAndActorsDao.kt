package ua.kolot.myacademyproject.cache

import androidx.room.*

@Dao
interface MovieWithGenresAndActorsDao {

    @Transaction
    @Query("SELECT * FROM "+ Contract.TABLE_NAME_MOVIES)
    fun getMovieWithActorsAndGenres(): List<MovieWithActorsAndGenres>

    @Transaction
    @Query("SELECT * FROM "+ Contract.TABLE_NAME_MOVIES + " WHERE " + Contract.COLUMN_NAME_ID_MOVIE + " = :movieId")
    fun getMovieWithActorsAndGenresById(movieId:Long): List<MovieWithActorsAndGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieGenreCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieGenreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieActorCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllActors(movies: List<MovieActorCrossRef>)

}