package ua.kolot.myacademyproject.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.data.Movie

@ExperimentalSerializationApi
class MovieListViewModel(private val movieListInteractor: MovieListInteractor) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _error.postValue(exception.message)
    }

    private var scope = CoroutineScope(
        Job() + Dispatchers.Default + exceptionHandler
    )

    private val _progress = MutableLiveData(false)
    private val _error = MutableLiveData<String>()

    private val _mutableMovies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _mutableMovies
    val progress: LiveData<Boolean> = _progress
    val error: LiveData<String> = _error

    init {
        scope.launch {
            _progress.postValue(true)

            try {
                val cached = movieListInteractor.getCachedMovies()
                _mutableMovies.postValue(cached)
                if (cached.isEmpty()) {
                    _mutableMovies.postValue(movieListInteractor.getRefreshedMovies())
                }
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