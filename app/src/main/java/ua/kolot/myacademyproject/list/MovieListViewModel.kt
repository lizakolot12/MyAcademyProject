package ua.kolot.myacademyproject.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ua.kolot.myacademyproject.data.MovieBase

class MovieListViewModel(private val movieListInteractor: MovieListInteractor) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _error.postValue(exception.message)
    }

    private var scope = CoroutineScope(
        Job() + Dispatchers.Default + exceptionHandler
    )

    private val _progress = MutableLiveData(false)
    private val _movies = MutableLiveData<List<MovieBase>>(emptyList())
    private val _error = MutableLiveData<String>()

    val movies: LiveData<List<MovieBase>> = _movies
    val progress: LiveData<Boolean> = _progress
    val error: LiveData<String> = _error

    init {
        scope.launch {
            _progress.postValue(true)

            try {
                val movies = movieListInteractor.getMovies()
                _movies.postValue(movies)
            } finally {
                _progress.postValue(false)
            }
        }
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}