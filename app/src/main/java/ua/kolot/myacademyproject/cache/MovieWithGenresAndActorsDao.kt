package ua.kolot.myacademyproject.cache

import androidx.room.*

@Dao
interface MovieWithGenresAndActorsDao {

    @Transaction
    @Query("SELECT * FROM movies")
    fun getMovieWithActorsAndGenres(): List<MovieWithActorsAndGenres>

    @Transaction
    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieWithActorsAndGenresById(movieId: Int): MovieWithActorsAndGenres?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieGenreCrossRefEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieGenreCrossRefEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieActorCrossRefEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllActors(movies: List<MovieActorCrossRefEntity>)

}