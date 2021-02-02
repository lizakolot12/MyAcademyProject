package ua.kolot.myacademyproject.network

import retrofit2.http.GET
import retrofit2.http.Path
import ua.kolot.myacademyproject.data.back.ActorsResponse
import ua.kolot.myacademyproject.data.back.GenreResponse
import ua.kolot.myacademyproject.data.back.MoviesResponse

interface MoviesApi {
    @GET("movie/now_playing")
    suspend fun getMovies(): MoviesResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(@Path("movie_id") movieId: Int): ActorsResponse
}