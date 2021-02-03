package ua.kolot.myacademyproject.cache

import android.content.Context
import ua.kolot.myacademyproject.data.Movie

class CacheDataSource(appContext: Context) {

    private val db = AppDatabase.getInstance(context = appContext)

    fun saveGenres(genres: List<GenreEntity>) {
        db.genreDao().insertAll(genres)
    }

    private fun saveActors(actors: List<ActorEntity>) {
        db.actorDao().insertAll(actors)
    }

    fun saveMovieWithActors(movieId: Int, actors: List<ActorEntity>) {
        saveActors(actors)
        val list = actors.map { actorEntity -> MovieActorCrossRef(movieId, actorEntity.id) }
        db.moviesWithGenresAndActorsDao().insertAllActors(list)
    }

    fun saveMoviesWithGenres(movies: List<Movie>) {
        val resultMovieGenreCrossRef = mutableListOf<MovieGenreCrossRef>()
        val resultMovies = movies.map { movie ->
            resultMovieGenreCrossRef.addAll(movie.genres.map { genre ->
                MovieGenreCrossRef(
                    movie.id,
                    genre.id
                )
            })
            MovieEntity(
                movie.id,
                movie.title,
                movie.overview,
                movie.poster,
                movie.backdrop,
                movie.ratings,
                movie.ratingNumber,
                movie.minimumAge
            )
        }

        db.movieDao().insertAll(resultMovies)
        db.moviesWithGenresAndActorsDao().insertAll(resultMovieGenreCrossRef)
    }

    fun getMoviesWithGenresAndActors(): List<MovieWithActorsAndGenres> {
        return db.moviesWithGenresAndActorsDao().getMovieWithActorsAndGenres()
    }

    fun getMoviesWithGenresAndActorsByMovieId(movieId: Int): MovieWithActorsAndGenres {
        return db.moviesWithGenresAndActorsDao().getMovieWithActorsAndGenresById(movieId)
    }
}
