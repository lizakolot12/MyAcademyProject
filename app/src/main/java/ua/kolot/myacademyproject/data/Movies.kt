package ua.kolot.myacademyproject.data

import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.data.back.ActorDTO
import ua.kolot.myacademyproject.data.back.GenreDTO
import ua.kolot.myacademyproject.data.back.MovieDTO
import ua.kolot.myacademyproject.network.RetrofitModule

@ExperimentalSerializationApi
object MoviesNetworkDataSource {

    private val api = RetrofitModule.moviesApi

    suspend fun getMovies(): List<MovieDTO> {
        return api.getMovies().movies
    }

    suspend fun getGenres(): List<GenreDTO> {
        return api.getGenres().genres
    }

    suspend fun getActorsByMovieId(id: Int): List<ActorDTO> {
        return api.getActors(id).actors
    }
}

