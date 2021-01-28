package ua.kolot.myacademyproject.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.cache.CacheDataSource
import ua.kolot.myacademyproject.cache.GenreEntity
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesNetworkDataSource
import ua.kolot.myacademyproject.mapMovieDtoWithoutActors
import ua.kolot.myacademyproject.mapToMovie

@ExperimentalSerializationApi
class MovieListInteractor(appContext: Context) {

    private val cacheDataSource = CacheDataSource(appContext)
    private val networkDataSource = MoviesNetworkDataSource

    private val mutableMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = mutableMovies

    suspend fun updateMovies() {
        val initialList =
            cacheDataSource.getMoviesWithGenresAndActors().map { item -> mapToMovie(item) }

        mutableMovies.postValue(initialList)

        val genres = networkDataSource.getGenres()
        val genresEntity =
            genres.map { genresDto -> GenreEntity(genresDto.id.toLong(), genresDto.name) }
        cacheDataSource.saveGenres(genresEntity)

        val movies = networkDataSource.getMovies()
        val moviesRes = movies.map { movieDTO ->
            mapMovieDtoWithoutActors(movieDTO, genres)
        }

        cacheDataSource.saveMoviesWithGenres(moviesRes)

        mutableMovies.postValue(moviesRes)
    }
}