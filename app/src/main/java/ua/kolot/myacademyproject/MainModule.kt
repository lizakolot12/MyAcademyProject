package ua.kolot.myacademyproject

import android.content.Context
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.cache.AppDatabase
import ua.kolot.myacademyproject.cache.CacheDataSource
import ua.kolot.myacademyproject.data.MoviesNetworkDataSource

@ExperimentalSerializationApi
object MainModule {
    private var INSTANCE_VIEWMODEL_FACTORY: ViewModelFactory? = null

    fun getViewModelFactory(context: Context): ViewModelFactory =
        INSTANCE_VIEWMODEL_FACTORY
            ?: createViewModelFactory(context).also { INSTANCE_VIEWMODEL_FACTORY = it }

    private fun createViewModelFactory(context: Context): ViewModelFactory {
        return ViewModelFactory(
            getCacheDataSource(context),
            moviesNetworkDataSource
        )
    }

    private val moviesNetworkDataSource = MoviesNetworkDataSource

    private fun getCacheDataSource(context: Context): CacheDataSource {
        val db = AppDatabase.getInstance(context)
        return CacheDataSource(
            db.genreDao(),
            db.actorDao(),
            db.moviesWithGenresAndActorsDao(),
            db.movieDao()
        )
    }
}