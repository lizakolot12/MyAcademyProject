package ua.kolot.myacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.data.Movie
import ua.kolot.myacademyproject.data.MoviesDataSource

class FragmentMoviesDetails : Fragment(), View.OnClickListener {

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int): FragmentMoviesDetails {
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieId)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var content: Group

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view.findViewById<View>(R.id.tv_back).setOnClickListener(this)
        view.findViewById<View>(R.id.iv_back).setOnClickListener(this)
        content = view.findViewById(R.id.content)

        val movieId = arguments?.getInt(MOVIE_ID)
        val movie = movieId?.let { MoviesDataSource.getMovieById(it) }

        movie?.let { showContent(view, it) } ?: run { showErrorContent() }
        return view
    }

    private fun showContent(view: View, movie: Movie) {
        view.findViewById<TextView>(R.id.tv_movie_title).text = movie.title
        view.findViewById<TextView>(R.id.tv_categories).text = movie.categories
        view.findViewById<RatingBar>(R.id.ratings).rating = movie.rating
        view.findViewById<TextView>(R.id.tv_reviews).text =
            getString(R.string.some_reviews, movie.reviews)
        view.findViewById<TextView>(R.id.tv_movie_title).text = movie.title

        val actorsRecyclerView = view.findViewById<RecyclerView>(R.id.rv_actors)
        actorsRecyclerView.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        actorsRecyclerView.adapter = ActorsAdapter(requireContext(), movie.actors)
    }

    private fun showErrorContent() {
        Toast.makeText(context, R.string.movie_error, Toast.LENGTH_LONG).show()
        content.visibility = View.INVISIBLE
    }

    override fun onClick(v: View?) {
        activity?.onBackPressed()
    }
}