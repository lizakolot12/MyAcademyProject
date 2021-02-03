package ua.kolot.myacademyproject.item

import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.cache.CacheDataSource
import ua.kolot.myacademyproject.cache.Mapper
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesNetworkDataSource

@ExperimentalSerializationApi
class MovieInteractor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: MoviesNetworkDataSource
) {

    fun getCachedMovieById(movieId: Int): Movie {
        return Mapper.mapToMovie(cacheDataSource.getMoviesWithGenresAndActorsByMovieId(movieId = movieId))
    }

    suspend fun getRefreshedMovieById(movieId: Int): Movie {
        val updatedActorsDto = networkDataSource.getActorsByMovieId(movieId.toInt())
        val updatedActorsEntity = updatedActorsDto.map { actorDto ->
            Mapper.mapToActorEntity(actorDto)
        }
        cacheDataSource.saveMovieWithActors(movieId, updatedActorsEntity)

        val updatedActors = updatedActorsDto.map { actorDto ->
            Mapper.mapToActor(actorDto)
        }
        return Mapper.mapToMovie(
            cacheDataSource.getMoviesWithGenresAndActorsByMovieId(movieId = movieId),
            updatedActors
        )
    }

}
