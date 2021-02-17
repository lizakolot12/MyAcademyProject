package ua.kolot.myacademyproject.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kolot.myacademyproject.MainModule
import ua.kolot.myacademyproject.R
import ua.kolot.myacademyproject.data.Movie

@ExperimentalSerializationApi
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

    private lateinit var titleView: TextView
    private lateinit var categoriesView: TextView
    private lateinit var ratingsView: RatingBar
    private lateinit var reviewsView: TextView
    private lateinit var requiredAgeView: TextView
    private lateinit var actorsRecyclerView: RecyclerView
    private lateinit var posterView: ImageView
    private lateinit var castView: TextView
    private lateinit var content: Group
    private lateinit var progressBar: ProgressBar

    private var actorAdapter: ActorsAdapter? = null

    private val viewModel: MovieViewModel by viewModels {
        MainModule.getViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view.findViewById<View>(R.id.tv_back).setOnClickListener(this)
        view.findViewById<View>(R.id.iv_back).setOnClickListener(this)

        progressBar = view.findViewById(R.id.progress)
        content = view.findViewById(R.id.content)
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
        actorAdapter =
            ActorsAdapter(requireContext())
        actorsRecyclerView.adapter = actorAdapter

        viewModel.currentMovie.observe(viewLifecycleOwner, ::updateViews)
        viewModel.progress.observe(viewLifecycleOwner, ::showProgress)
        viewModel.error.observe(viewLifecycleOwner, ::showError)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getMovie(arguments?.getInt(MOVIE_ID))
        }
    }

    private fun showError(error: String?) {
        error?.let { Toast.makeText(context, error, Toast.LENGTH_LONG).show() }
    }

    private fun showProgress(progress: Boolean) {
        progressBar.visibility = if (progress) View.VISIBLE else View.INVISIBLE
        content.visibility = if (!progress) View.VISIBLE else View.INVISIBLE
    }

    private fun updateViews(movie: Movie) {
        titleView.text = movie.title
        categoriesView.text = movie.genres.joinToString { it.name }
        ratingsView.rating = movie.ratings / 2
        reviewsView.text =
            getString(R.string.some_reviews, movie.ratingNumber)
        requiredAgeView.text = getString(R.string.minimum_age, movie.minimumAge)
        castView.visibility = if (movie.actors.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }

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

}