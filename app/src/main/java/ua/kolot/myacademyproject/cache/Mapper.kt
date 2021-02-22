package ua.kolot.myacademyproject.cache

import ua.kolot.myacademyproject.data.Actor
import ua.kolot.myacademyproject.data.Genre
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.back.ActorDTO
import ua.kolot.myacademyproject.data.back.GenreDTO
import ua.kolot.myacademyproject.data.back.MovieDTO

object Mapper {
    private const val PICTURE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    private const val LABEL_AGE_ADULT = 16
    private const val LABEL_AGE_CHILD = 13

    fun mapToMovie(movieCached: MovieWithActorsAndGenres?): Movie? {
        return movieCached?.let {
            Movie(
                movieCached.movieEntity.id.toInt(),
                movieCached.movieEntity.title,
                movieCached.movieEntity.overview,
                movieCached.movieEntity.poster,
                movieCached.movieEntity.backdrop,
                movieCached.movieEntity.ratings,
                movieCached.movieEntity.ratingNumber,
                movieCached.movieEntity.minimumAge,
                movieCached.genres.map { genresEntity ->
                    Genre(
                        genresEntity.id.toInt(),
                        genresEntity.name
                    )
                },
                movieCached.actors?.map { actorEntity ->
                    Actor(
                        actorEntity.id,
                        actorEntity.name,
                        actorEntity.picture
                    )
                } ?: emptyList()
            )
        }
    }

    fun mapMovieDtoWithoutActors(movieDTO: MovieDTO, genres: List<GenreDTO>): Movie {
        val genresMap = genres.associateBy { it.id }
        return Movie(
            id = movieDTO.id,
            title = movieDTO.title,
            overview = movieDTO.overview,
            poster = PICTURE_BASE_URL + movieDTO.posterPicture,
            backdrop = movieDTO.backdropPicture,
            ratings = movieDTO.ratings,
            ratingNumber = movieDTO.votesCount,
            minimumAge = if (movieDTO.adult) LABEL_AGE_ADULT else LABEL_AGE_CHILD,
            genres = movieDTO.genreIds.map {
                Genre(genresMap[it]?.id ?: -1, genresMap[it]?.name ?: "")
            }
        )
    }

    fun mapToActorEntity(actorDto: ActorDTO): ActorEntity {
        return ActorEntity(
            actorDto.id,
            actorDto.name,
            PICTURE_BASE_URL + actorDto.profilePath
        )
    }

    fun mapToActor(actorDto: ActorDTO): Actor {
        return Actor(
            actorDto.id,
            actorDto.name,
            PICTURE_BASE_URL + actorDto.profilePath
        )
    }

    fun mapToMovie(
        movieCached: MovieWithActorsAndGenres?,
        updatedActors: List<Actor>
    ): Movie? {
        return movieCached?.let {
            Movie(
                movieCached.movieEntity.id.toInt(),
                movieCached.movieEntity.title,
                movieCached.movieEntity.overview,
                movieCached.movieEntity.poster,
                movieCached.movieEntity.backdrop,
                movieCached.movieEntity.ratings,
                movieCached.movieEntity.ratingNumber,
                movieCached.movieEntity.minimumAge,
                movieCached.genres.map { genresEntity ->
                    Genre(
                        genresEntity.id,
                        genresEntity.name
                    )
                },
                updatedActors
            )
        }
    }
}