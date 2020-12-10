package ua.kolot.myacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.data.MoviesDataSource

class FragmentMoviesList : Fragment() {

    companion object {
        private const val GRID_COLUMN = 2

        fun newInstance() =
            FragmentMoviesList()

    }

    private var movieClickListener: MovieClickListener? = null

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
        recyclerView.adapter = MoviesAdapter(requireContext(), MoviesDataSource.movies, movieClickListener)
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
}