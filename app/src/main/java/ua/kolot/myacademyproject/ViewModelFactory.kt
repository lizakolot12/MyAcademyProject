package ua.kolot.myacademyproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.cache.CacheDataSource
import ua.kolot.myacademyproject.data.MoviesNetworkDataSource
import ua.kolot.myacademyproject.item.MovieInteractor
import ua.kolot.myacademyproject.item.MovieViewModel
import ua.kolot.myacademyproject.list.MovieListInteractor
import ua.kolot.myacademyproject.list.MovieListViewModel

@ExperimentalSerializationApi
class ViewModelFactory(private var context: Context) : ViewModelProvider.Factory {

    private val cacheDataSource: CacheDataSource = CacheDataSource(context)
    private val moviesNetworkDataSource = MoviesNetworkDataSource

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieViewModel::class.java -> MovieViewModel(
            MovieInteractor(
                cacheDataSource,
                moviesNetworkDataSource
            )
        )
        MovieListViewModel::class.java -> MovieListViewModel(
            MovieListInteractor(
                cacheDataSource, moviesNetworkDataSource
            )
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}