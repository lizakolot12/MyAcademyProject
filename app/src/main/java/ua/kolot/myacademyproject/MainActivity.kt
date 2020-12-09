package ua.kolot.myacademyproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MovieClickListener {

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
            .add(R.id.container, FragmentMoviesDetails.newInstance(movieId)).commit()
    }
}