package ua.kolot.myacademyproject.cache

import android.content.Context
import androidx.room.Transaction
import ua.kolot.myacademyproject.data.Movie

class CacheDataSource(appContext: Context) {

    private val db = AppDatabase.create(appContext)

    fun saveGenres(genres: List<GenreEntity>) {
        db.genreDao().insertAll(genres)
    }

    private fun saveActors(actors: List<ActorEntity>) {
        db.actorDao().insertAll(actors)
    }

    fun saveMovieWithActors(movieId: Long, actors: List<ActorEntity>) {
        saveActors(actors)
        val list = mutableListOf<MovieActorCrossRef>()
        actors.forEach { actorEntity -> list.add(MovieActorCrossRef(movieId, actorEntity.id)) }
        db.moviesWithGenresAndActorsDao().insertAllActors(list)
    }

    fun saveMoviesWithGenres(movies: List<Movie>) {
        val resultMovieGenreCrossRef = mutableListOf<MovieGenreCrossRef>()
        val resultMovies = mutableListOf<MovieEntity>()

        movies.forEach { movie ->
            resultMovies.add(
                MovieEntity(
                    movie.id.toLong(),
                    movie.title,
                    movie.overview,
                    movie.poster,
                    movie.backdrop,
                    movie.ratings,
                    movie.ratingNumber,
                    movie.minimumAge
                )
            )
            movie.genres.forEach { genre ->
                resultMovieGenreCrossRef.add(
                    MovieGenreCrossRef(
                        movie.id.toLong(),
                        genre.id.toLong()
                    )
                )
            }
        }

        db.movieDao().insertAll(resultMovies)
        db.moviesWithGenresAndActorsDao().insertAll(resultMovieGenreCrossRef)
    }

    fun getMoviesWithGenresAndActors(): List<MovieWithActorsAndGenres> {
        return db.moviesWithGenresAndActorsDao().getMovieWithActorsAndGenres()
    }

    fun getMoviesWithGenresAndActorsByMovieId(movieId: Long): List<MovieWithActorsAndGenres> {
        return db.moviesWithGenresAndActorsDao().getMovieWithActorsAndGenresById(movieId)
    }
}
