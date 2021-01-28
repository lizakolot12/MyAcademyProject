package ua.kolot.myacademyproject.item

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.data.Movie

@ExperimentalSerializationApi
class MovieViewModel(private val movieInteractor: MovieInteractor) : ViewModel() {

    private val _currentMovie = MediatorLiveData<Movie>()
    private val _progress = MutableLiveData<Boolean>()
    private val _error = MediatorLiveData<String>()

    val currentMovie: LiveData<Movie> = _currentMovie
    val progress: LiveData<Boolean> = _progress
    val error: LiveData<String> = _error

    init {
        _error.addSource(movieInteractor.error) { error -> _error.postValue(error) }
    }

    fun getMovie(movieId: Int?) {
        if (movieId == null) {
            _error.value = "Movie id is null"
            return
        }

        _progress.value = true
        try {
            val movieLiveData = movieInteractor.getMovieById(movieId.toLong())
            _currentMovie.addSource(movieLiveData) { movie -> _currentMovie.postValue(movie) }
        } finally {
            _progress.postValue(false)
        }
    }

}