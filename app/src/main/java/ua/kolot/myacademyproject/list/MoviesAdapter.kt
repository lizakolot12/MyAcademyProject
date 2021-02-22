package ua.kolot.myacademyproject.list

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.R
import ua.kolot.myacademyproject.data.Movie

class MoviesAdapter(
    context: Context,
    private val movieClickListener: MovieClickListener?
) : RecyclerView.Adapter<MovieViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            inflater.inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener { movieClickListener?.onMovieClick(movies[position].id) }
    }

    fun updateData(movies: List<Movie>) {
        this.movies = movies
        Log.e("TEST", " update data")
        notifyDataSetChanged()
    }
}