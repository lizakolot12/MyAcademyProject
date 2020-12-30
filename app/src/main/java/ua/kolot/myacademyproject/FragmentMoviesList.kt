package ua.kolot.myacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesDataSource
import ua.kolot.myacademyproject.util.SpacingItemDecorator

class FragmentMoviesList : Fragment() {

    companion object {
        private const val GRID_COLUMN = 2

        fun newInstance() =
            FragmentMoviesList()

    }

    private var movieClickListener: MovieClickListener? = null
    private lateinit var adapter: MoviesAdapter

    private var scope = CoroutineScope(
        Job() +
                Dispatchers.Default
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_movies)
        recyclerView.layoutManager = GridLayoutManager(context, GRID_COLUMN)
        recyclerView.addItemDecoration(SpacingItemDecorator(16))
        adapter = MoviesAdapter(requireContext(), emptyList(), movieClickListener)
        recyclerView.adapter = adapter

        loadData()
    }

    private fun loadData() {
        scope.launch {
            val moviesList = MoviesDataSource.getMovies(requireContext())
            updateViews(moviesList)
        }
    }

    private suspend fun updateViews(list: List<Movie>?) = withContext(Dispatchers.Main) {
        list?.let { movies -> adapter.updateData(movies) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieClickListener) {
            movieClickListener = context
        }
    }

    override fun onDetach() {
        movieClickListener = null
        super.onDetach()
    }

    override fun onDestroyView() {
        scope.cancel()
        super.onDestroyView()
    }
}