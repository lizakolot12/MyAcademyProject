package ua.kolot.myacademyproject.item

import android.content.Context
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesDataSource

class MovieInteractor(private val context: Context) {

    suspend fun movie(movieId:Int): Movie? {
        return MoviesDataSource.getMovieById(movieId, context)
    }
}