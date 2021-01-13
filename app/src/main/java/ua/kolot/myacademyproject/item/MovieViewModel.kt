package ua.kolot.myacademyproject.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ua.kolot.myacademyproject.data.Movie

class MovieViewModel(private val movieInteractor: MovieInteractor) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _error.postValue(exception.message)
    }

    private var scope = CoroutineScope(
        Job() + Dispatchers.Default + exceptionHandler
    )

    private val _currentMovie = MutableLiveData<Movie>()
    private val _progress = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val currentMovie: LiveData<Movie> = _currentMovie
    val progress: LiveData<Boolean> = _progress
    val error: LiveData<String> = _error

    fun getMovie(movieId: Int?) {
        if (movieId == null) {
            _error.value = "Movie id is null"
            return
        }

        _progress.value = true
        scope.launch {
            try {
                val movie = movieInteractor.getMovieById(movieId)
                _currentMovie.postValue(movie)
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