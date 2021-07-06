package roemah.kreator.moviecat.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import roemah.kreator.moviecat.R
import roemah.kreator.moviecat.databinding.FragmentMovieBinding
import roemah.kreator.moviecat.viewmodel.CatalogViewModelFactory


class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = CatalogViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            val movieAdapter = MovieAdapter()
//            movieAdapter.setMovies(movies)
            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(this, { courses ->
                fragmentMovieBinding.progressBar.visibility = View.GONE
                movieAdapter.setMovies(courses)
                movieAdapter.notifyDataSetChanged()
            })

            with(fragmentMovieBinding.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

}