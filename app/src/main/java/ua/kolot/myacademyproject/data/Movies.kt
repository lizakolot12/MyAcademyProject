package ua.kolot.myacademyproject.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.network.RetrofitModule

@ExperimentalSerializationApi
object MoviesDataSource {
    private var movies: List<MovieBase>? = null
    private val api = RetrofitModule.moviesApi

    suspend fun getMovies(): List<MovieBase> = withContext(Dispatchers.IO) {
        if (movies == null) {
            movies = loadMovies()
        }
        return@withContext movies as List<MovieBase>
    }

    private suspend fun loadMovies(): List<MovieBase> = withContext(Dispatchers.IO) {
        val genres = api.genres().genres
        val genresMap = genres.associateBy { it.id }
        val movies = api.getMovies().movies

        return@withContext movies.map { jsonMovie ->

            MovieBase(
                id = jsonMovie.id,
                title = jsonMovie.title,
                overview = jsonMovie.overview,
                poster = "https://image.tmdb.org/t/p/w500" + jsonMovie.posterPicture,
                backdrop = jsonMovie.backdropPicture,
                ratings = jsonMovie.ratings,
                numberOfRatings = jsonMovie.votesCount,
                minimumAge = if (jsonMovie.adult) 16 else 13,
                genres = jsonMovie.genreIds.map {
                    Genre(genresMap[it]?.id ?: -1, genresMap[it]?.name ?: "")
                }
            )
        }
    }

    suspend fun getMovieById(id: Int): Movie? = withContext(Dispatchers.IO) {
        val currentMovies = movies ?: getMovies()
        val movieBase = currentMovies.firstOrNull { movie -> movie.id == id }
        val actorsList = api.actors(id).actors

        val actorsNew = actorsList.map { json ->
            Actor(
                json.id,
                json.name,
                "https://image.tmdb.org/t/p/w500" + json.profilePath
            )
        }
        if (movieBase != null) return@withContext Movie(movieBase, actorsNew)
        return@withContext null
    }
}

