package ua.kolot.myacademyproject.list

import android.content.Context
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesDataSource

class MovieListInteractor(private val context: Context) {

    suspend fun getMovies(): List<Movie>? {
        return MoviesDataSource.getMovies(context)
    }
}