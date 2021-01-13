package ua.kolot.myacademyproject.list

import ua.kolot.myacademyproject.data.MovieBase
import ua.kolot.myacademyproject.data.MoviesDataSource

class MovieListInteractor() {

    suspend fun getMovies(): List<MovieBase> {
        return MoviesDataSource.getMovies()
    }
}