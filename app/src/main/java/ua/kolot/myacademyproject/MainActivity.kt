package ua.kolot.myacademyproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.kolot.myacademyproject.item.FragmentMoviesDetails
import ua.kolot.myacademyproject.list.FragmentMoviesList
import ua.kolot.myacademyproject.list.MovieClickListener

class MainActivity : AppCompatActivity(),
    MovieClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FragmentMoviesList.newInstance()).commit()
        }
    }

    override fun onMovieClick(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, FragmentMoviesDetails.newInstance(movieId)).commit()
    }
}