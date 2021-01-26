package ua.kolot.myacademyproject.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.R
import ua.kolot.myacademyproject.ViewModelFactory
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.util.SpacingItemDecorator

class FragmentMoviesList : Fragment() {

    companion object {
        private const val GRID_COLUMN = 2

        fun newInstance() =
            FragmentMoviesList()

    }

    private var movieClickListener: MovieClickListener? = null
    private lateinit var adapter: MoviesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val viewModel: MovieListViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress)
        recyclerView = view.findViewById(R.id.rv_movies)
        recyclerView.layoutManager = GridLayoutManager(
            context,
            GRID_COLUMN
        )
        recyclerView.addItemDecoration(SpacingItemDecorator(16))
        adapter = MoviesAdapter(
            requireContext(),
            movieClickListener
        )
        recyclerView.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, ::updateList)
        viewModel.progress.observe(viewLifecycleOwner, ::showProgress)
        viewModel.error.observe(viewLifecycleOwner, ::showError)

    }

    private fun updateList(list: List<Movie>?) {
        list?.let(adapter::updateData)
    }

    private fun showProgress(progress: Boolean) {
        progressBar.visibility = if (progress) View.VISIBLE else View.INVISIBLE
    }

    private fun showError(error: String?) {
        error?.let { Toast.makeText(context, error, Toast.LENGTH_LONG).show() }
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