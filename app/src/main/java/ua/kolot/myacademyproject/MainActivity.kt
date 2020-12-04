package ua.kolot.myacademyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), MovieClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FragmentMoviesList.newInstance()).commit()
        }
    }

    override fun onMovieClick() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, FragmentMoviesDetails.newInstance()).commit()
    }
}