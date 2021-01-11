package ua.kolot.myacademyproject.network

import retrofit2.http.GET
import retrofit2.http.Path
import ua.kolot.myacademyproject.data.back.ActorsResult
import ua.kolot.myacademyproject.data.back.GenreResult
import ua.kolot.myacademyproject.data.back.MoviesResult

interface MoviesApi {
    @GET("movie/now_playing")
    suspend fun getMovies(): MoviesResult

    @GET("genre/movie/list")
    suspend fun genres(): GenreResult

    /* @GET("/movie/{movie_id}")
     suspend fun movieDetails(@Path("movie_id")movieId:String):*/

    @GET("movie/{movie_id}/credits")
    suspend fun actors(@Path("movie_id") movieId: Int): ActorsResult
}