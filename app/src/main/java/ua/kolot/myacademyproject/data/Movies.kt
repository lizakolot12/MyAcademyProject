package ua.kolot.myacademyproject.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.network.RetrofitModule

@ExperimentalSerializationApi
object MoviesDataSource {

    private const val PICTURE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    private const val LABEL_AGE_ADULT = 16
    private const val LABEL_AGE_CHILD = 13

    private var movies: List<Movie>? = null
    private val api = RetrofitModule.moviesApi

    suspend fun getMovies(): List<Movie>? = withContext(Dispatchers.IO) {
        if (movies == null) {
            movies = loadMovies()
        }
        return@withContext movies
    }

    private suspend fun loadMovies(): List<Movie> {
        val genres = api.getGenres().genres
        val genresMap = genres.associateBy { it.id }
        val movies = api.getMovies().movies

        return  movies.map { movieDTO ->

            Movie(
                id = movieDTO.id,
                title = movieDTO.title,
                overview = movieDTO.overview,
                poster = PICTURE_BASE_URL + movieDTO.posterPicture,
                backdrop = movieDTO.backdropPicture,
                ratings = movieDTO.ratings,
                ratingNumber = movieDTO.votesCount,
                minimumAge = if (movieDTO.adult) LABEL_AGE_ADULT else LABEL_AGE_CHILD,
                genres = movieDTO.genreIds.map {
                    Genre(genresMap[it]?.id ?: -1, genresMap[it]?.name ?: "")
                },
                null
            )
        }
    }

    suspend fun getMovieById(id: Int): Movie? = withContext(Dispatchers.IO) {
        val currentMovies = movies ?: getMovies()
        val movie = currentMovies?.firstOrNull { movie -> movie.id == id }
        val actorsList = api.getActors(id).actors

        val actorsNew = actorsList.map { actorDto ->
            Actor(
                actorDto.id,
                actorDto.name,
                PICTURE_BASE_URL + actorDto.profilePath
            )
        }
        movie?.actors = actorsNew
        return@withContext movie
    }
}

