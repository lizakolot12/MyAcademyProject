package ua.kolot.myacademyproject.list

import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.cache.CacheDataSource
import ua.kolot.myacademyproject.cache.GenreEntity
import ua.kolot.myacademyproject.cache.Mapper
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesNetworkDataSource

@ExperimentalSerializationApi
class MovieListInteractor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: MoviesNetworkDataSource
) {


    suspend fun getCachedMovies(): List<Movie> {
        return cacheDataSource.getMoviesWithGenresAndActors()
            .map { item -> Mapper.mapToMovie(item) }

    }

    suspend fun getRefreshedMovies(): List<Movie> {

        val genres = networkDataSource.getGenres()
        val genresEntity =
            genres.map { genresDto -> GenreEntity(genresDto.id, genresDto.name) }
        cacheDataSource.saveGenres(genresEntity)

        val movies = networkDataSource.getMovies()
        val moviesRes = movies.map { movieDTO ->
            Mapper.mapMovieDtoWithoutActors(movieDTO, genres)
        }

        cacheDataSource.saveMoviesWithGenres(moviesRes)

        return moviesRes
    }

}