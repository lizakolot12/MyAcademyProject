package ua.kolot.myacademyproject.list

import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesDataSource

@ExperimentalSerializationApi
class MovieListInteractor() {

    suspend fun getMovies(): List<Movie>? {
        return MoviesDataSource.getMovies()
    }
}