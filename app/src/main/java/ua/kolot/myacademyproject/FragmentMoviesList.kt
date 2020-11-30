package ua.kolot.myacademyproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentMoviesList : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() =
                FragmentMoviesList()

    }

    private var movieClickListener: MovieClickListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view.findViewById<View>(R.id.card)?.setOnClickListener {
            movieClickListener?.onMovieClick()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieClickListener) {
            movieClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        movieClickListener = null
    }

}