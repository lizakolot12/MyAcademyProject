package ua.kolot.myacademyproject.data

import ua.kolot.myacademyproject.R


object MoviesDataSource {

    private val movies: List<Movie> = listOf(
        Movie(
            1,
            "Avengers:The game",
            "Action, Adventure, Drama",
            R.drawable.poster_avengers,
            137,
            4f,
            198,
            listOf(Actor("ActorName1", R.drawable.ruffalo), Actor("ActorName2", R.drawable.evans))
        ),

        Movie(
            2, "Tenet", "Action, Sci-Fi, Thriller", R.drawable.tenet, 97, 5f, 98,
            listOf(
                Actor("ActorName1", R.drawable.evans),
                Actor("ActorName2", R.drawable.downey),
                Actor("ActorName3", R.drawable.ruffalo),
                Actor("ActorName3", R.drawable.hemsworth)
            )
        ),

        Movie(
            3, "Black Widow", "Action, Adventure, Sci-Fi", R.drawable.black_widow, 108, 5f, 98,
            listOf(Actor("ActorName4", R.drawable.ruffalo), Actor("ActorName8", R.drawable.evans))
        ),

        Movie(
            4,
            "Wonder Women 1984",
            "Action, Adventure, Fantasy",
            R.drawable.wonder_women,
            132,
            5f,
            48,
            listOf(Actor("ActorName1", R.drawable.ruffalo), Actor("ActorName2", R.drawable.evans))
        ),

        Movie(
            5, "Iconman", "Action, Adventure, Sci-Fi", R.drawable.iconman, 95, 2f, 123,
            listOf(Actor("ActorName4", R.drawable.ruffalo), Actor("ActorName8", R.drawable.evans))
        ),

        Movie(
            6, "Some films", "Action, Adventure", R.drawable.wonder_women, 132, 5f, 48,
            listOf(
                Actor("ActorName1", R.drawable.wonder_women),
                Actor("ActorName2", R.drawable.evans)
            )
        )
    )

    fun movies(): List<Movie> = movies
    fun getMovieById(id: Int): Movie =
        movies.first() { it.id == id }
}