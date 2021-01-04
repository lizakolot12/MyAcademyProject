package ua.kolot.myacademyproject.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MoviesDataSource {
    private var movies: List<Movie>? = null

    suspend fun getMovies(context: Context): List<Movie>? = withContext(Dispatchers.IO) {
        if (movies == null) {
            movies = loadMovies(context)
        }
        return@withContext movies
    }

    suspend fun getMovieById(id: Int, context: Context): Movie? = withContext(Dispatchers.IO) {
        val currentMovie = movies ?: getMovies(context)
        currentMovie?.firstOrNull { movie -> movie.id == id }
    }

}

