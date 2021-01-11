package ua.kolot.myacademyproject.list

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ua.kolot.myacademyproject.R
import ua.kolot.myacademyproject.data.MovieBase

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val titleView: TextView = itemView.findViewById(R.id.tv_movie_title)
    private val duration: TextView = itemView.findViewById(R.id.tv_duration)
    private val reviews: TextView = itemView.findViewById(R.id.tv_reviews)
    private val ratings: RatingBar = itemView.findViewById(R.id.ratings)
    private val requiredAge: TextView = itemView.findViewById(R.id.tv_required_age)
    private val categories: TextView = itemView.findViewById(R.id.tv_categories)

    fun bind(movie: MovieBase) {
        val context = itemView.context

        Glide.with(context)
            .load(movie.poster)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
            .placeholder(R.drawable.loading)
            .into(poster)

        titleView.text = movie.title
        duration.text = context.getString(R.string.some_minutes, 100)
        reviews.text = context.getString(R.string.some_reviews, movie.numberOfRatings)
        ratings.rating = movie.ratings / 2
        requiredAge.text = context.getString(R.string.minimum_age, movie.minimumAge)
        categories.text = movie.genres.joinToString { it.name }
    }
}