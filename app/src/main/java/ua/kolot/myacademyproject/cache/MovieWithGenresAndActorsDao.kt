package ua.kolot.myacademyproject.cache

import androidx.room.*

@Dao
interface MovieWithGenresAndActorsDao {

    @Transaction
    @Query("SELECT * FROM movies")
    fun getMovieWithActorsAndGenres(): List<MovieWithActorsAndGenres>

    @Transaction
    @Query("SELECT * FROM movies WHERE idMovie = :movieId")
    fun getMovieWithActorsAndGenresById(movieId: Int): MovieWithActorsAndGenres

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieGenreCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieGenreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieActorCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllActors(movies: List<MovieActorCrossRef>)

}