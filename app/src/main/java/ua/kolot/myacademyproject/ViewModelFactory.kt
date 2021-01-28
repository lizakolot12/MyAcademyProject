package ua.kolot.myacademyproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.item.MovieInteractor
import ua.kolot.myacademyproject.item.MovieViewModel
import ua.kolot.myacademyproject.list.MovieListInteractor
import ua.kolot.myacademyproject.list.MovieListViewModel

@ExperimentalSerializationApi
class ViewModelFactory(private var context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieViewModel::class.java -> MovieViewModel(MovieInteractor(context))
        MovieListViewModel::class.java -> MovieListViewModel(MovieListInteractor(context))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}