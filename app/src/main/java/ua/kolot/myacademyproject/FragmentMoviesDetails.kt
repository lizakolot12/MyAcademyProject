package ua.kolot.myacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.data.MoviesDataSource

class FragmentMoviesDetails : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view.findViewById<View>(R.id.tv_back).setOnClickListener(this)
        view.findViewById<View>(R.id.iv_back).setOnClickListener(this)

        val movie = MoviesDataSource.getMovieById(arguments?.getInt(MOVIE_ID) ?: 0)

        view.findViewById<TextView>(R.id.tv_movie_title).text = movie.title
        view.findViewById<TextView>(R.id.tv_categories).text = movie.categories
        view.findViewById<RatingBar>(R.id.ratings).rating = movie.rating
        view.findViewById<TextView>(R.id.tv_reviews).text =
            getString(R.string.some_reviews, movie.reviews)
        view.findViewById<TextView>(R.id.tv_movie_title).text = movie.title

        val actorsView = view.findViewById<RecyclerView>(R.id.actors)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = HORIZONTAL
        actorsView?.layoutManager = linearLayoutManager
        actorsView?.adapter = context?.let { ActorsAdapter(it, movie.actors) }
        return view
    }

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

    override fun onClick(v: View?) {
        activity?.onBackPressed()
    }
}