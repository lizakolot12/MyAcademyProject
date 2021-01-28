package ua.kolot.myacademyproject.item

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.cache.CacheDataSource
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesNetworkDataSource
import ua.kolot.myacademyproject.mapToActor
import ua.kolot.myacademyproject.mapToActorEntity
import ua.kolot.myacademyproject.mapToMovie

@ExperimentalSerializationApi
class MovieInteractor(appContext: Context) {

    private val cacheDataSource = CacheDataSource(appContext)
    private val networkDataSource = MoviesNetworkDataSource

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _error.postValue(exception.message)
    }
    private var scope = CoroutineScope(
        Job() + Dispatchers.Default + exceptionHandler
    )

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    @ExperimentalSerializationApi
    fun getMovieById(movieId: Long): LiveData<Movie> {
        val movie = MutableLiveData<Movie>()

        scope.launch {
            val movieCached =
                cacheDataSource.getMoviesWithGenresAndActorsByMovieId(movieId = movieId).first()

            movie.postValue(mapToMovie(movieCached))

            val updatedActorsDto = networkDataSource.getActorsByMovieId(movieId.toInt())
            val updatedActorsEntity = updatedActorsDto.map { actorDto ->
                mapToActorEntity(actorDto)
            }
            cacheDataSource.saveMovieWithActors(movieId, updatedActorsEntity)

            val updatedActors = updatedActorsDto.map { actorDto ->
                mapToActor(actorDto)
            }
            val updatedMovie = mapToMovie(movieCached, updatedActors)

            movie.postValue(updatedMovie)
        }
        return movie
    }

}
