package ua.kolot.myacademyproject

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.data.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val titleView: TextView = itemView.findViewById(R.id.tv_movie_title)
    private val duration: TextView = itemView.findViewById(R.id.tv_duration)
    private val reviews: TextView = itemView.findViewById(R.id.tv_reviews)
    private val ratings: RatingBar = itemView.findViewById(R.id.ratings)

    fun bind(movie: Movie) {
        poster.setImageDrawable(itemView.context.resources.getDrawable(movie.posterId))
        titleView.text = movie.title
        duration.text = itemView.context.getString(R.string.some_minutes, movie.duration)
        reviews.text = itemView.context.getString(R.string.some_reviews, movie.reviews)
        ratings.rating = movie.rating
    }
}