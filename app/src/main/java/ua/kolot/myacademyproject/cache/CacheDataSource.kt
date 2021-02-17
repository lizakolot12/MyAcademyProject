package ua.kolot.myacademyproject.cache

import ua.kolot.myacademyproject.data.Movie

class CacheDataSource(
    private val genreDao: GenreDao,
    private val actorDao: ActorDao,
    private val moviesWithGenresAndActorsDao: MovieWithGenresAndActorsDao,
    private val movieDao: MovieDao
) {

    fun saveGenres(genres: List<GenreEntity>) {
        genreDao.insertAll(genres)
    }

    private fun saveActors(actors: List<ActorEntity>) {
        actorDao.insertAll(actors)
    }

    fun saveMovieWithActors(movieId: Int, actors: List<ActorEntity>) {
        saveActors(actors)
        val list = actors.map { actorEntity -> MovieActorCrossRefEntity(movieId, actorEntity.id) }
        moviesWithGenresAndActorsDao.insertAllActors(list)
    }

    fun saveMoviesWithGenres(movies: List<Movie>) {
        val resultMovieGenreCrossRef = mutableListOf<MovieGenreCrossRefEntity>()
        val resultMovies = movies.map { movie ->
            resultMovieGenreCrossRef.addAll(movie.genres.map { genre ->
                MovieGenreCrossRefEntity(
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

        movieDao.insertAll(resultMovies)
        moviesWithGenresAndActorsDao.insertAll(resultMovieGenreCrossRef)
    }

    fun getMoviesWithGenresAndActors(): List<MovieWithActorsAndGenres> {
        return moviesWithGenresAndActorsDao.getMovieWithActorsAndGenres()
    }

    fun getMoviesWithGenresAndActorsByMovieId(movieId: Int): MovieWithActorsAndGenres? {
        return moviesWithGenresAndActorsDao.getMovieWithActorsAndGenresById(movieId)
    }
}
