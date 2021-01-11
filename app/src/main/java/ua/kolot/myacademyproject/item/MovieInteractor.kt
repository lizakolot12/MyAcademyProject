package ua.kolot.myacademyproject.item

import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesDataSource

class MovieInteractor() {

    suspend fun movie(movieId:Int): Movie? {
        return MoviesDataSource.getMovieById(movieId)
    }
}