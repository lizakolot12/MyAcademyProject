package ua.kolot.myacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
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

    private var scope = CoroutineScope(
        Job() +
                Dispatchers.Default
    )

    private lateinit var titleView: TextView
    private lateinit var categoriesView: TextView
    private lateinit var ratingsView: RatingBar
    private lateinit var reviewsView: TextView
    private lateinit var requiredAgeView: TextView
    private lateinit var actorsRecyclerView: RecyclerView
    private lateinit var posterView:ImageView
    private lateinit var castView:TextView

    private var actorAdapter: ActorsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view.findViewById<View>(R.id.tv_back).setOnClickListener(this)
        view.findViewById<View>(R.id.iv_back).setOnClickListener(this)

        titleView = view.findViewById(R.id.tv_movie_title)
        categoriesView = view.findViewById(R.id.tv_categories)
        ratingsView = view.findViewById(R.id.ratings)
        reviewsView = view.findViewById(R.id.tv_reviews)
        requiredAgeView = view.findViewById(R.id.tv_required_age)
        posterView = view.findViewById(R.id.iv_poster)
        castView = view.findViewById(R.id.tv_cast)

        castView.visibility = View.INVISIBLE

        actorsRecyclerView = view.findViewById(R.id.rv_actors)
        actorsRecyclerView.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        actorAdapter = ActorsAdapter(requireContext(), emptyList())
        actorsRecyclerView.adapter = actorAdapter

        val movieId = arguments?.getInt(MOVIE_ID) ?: error("Movie id is null")
        loadData(movieId)

        return view
    }

    private fun loadData(movieId: Int) {
        scope.launch {
            val movie = MoviesDataSource.getMovieById(movieId, requireContext())
            movie?.let { movieCurrent ->  updateViews(movieCurrent) }
        }
    }

    private suspend fun updateViews(movie: Movie) = withContext(Dispatchers.Main) {
        titleView.text = movie.title
        categoriesView.text = movie.genres.joinToString { it.name }
        ratingsView.rating = movie.ratings/2
        reviewsView.text =
            getString(R.string.some_reviews, movie.numberOfRatings)
        requiredAgeView.text = getString(R.string.minimum_age, movie.minimumAge)
        castView.visibility = if (movie.actors.size > 0) View.VISIBLE else View.INVISIBLE

        actorAdapter?.updateData(movie.actors)

        Glide
            .with(requireContext())
            .load(movie.poster)
            .placeholder(R.drawable.loading)
            .into(posterView)
    }

    override fun onClick(v: View?) {
        activity?.onBackPressed()
    }

    override fun onDestroyView() {
        scope.cancel()
        super.onDestroyView()
    }
}