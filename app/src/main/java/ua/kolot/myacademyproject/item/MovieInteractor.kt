package ua.kolot.myacademyproject.item

import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesDataSource

class MovieInteractor() {

    @ExperimentalSerializationApi
    suspend fun getMovieById(movieId:Int): Movie? {
        return MoviesDataSource.getMovieById(movieId)
    }
}