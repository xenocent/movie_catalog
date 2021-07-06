package roemah.kreator.moviecat.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import roemah.kreator.moviecat.R
import roemah.kreator.moviecat.databinding.FragmentTvBinding
import roemah.kreator.moviecat.viewmodel.CatalogViewModelFactory
import roemah.kreator.moviecat.viewmodel.ViewModelFactory


class TvFragment : Fragment() {

    private lateinit var fragmentTvBinding: FragmentTvBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater,container,false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = CatalogViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this,factory)[TvViewModel::class.java]
            val serie = viewModel.getTvs()

            val movieAdapter = TvAdapter()
            //  movieAdapter.setTv(serie)

            fragmentTvBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvs().observe(this, { courses ->
                fragmentTvBinding.progressBar.visibility = View.GONE
                movieAdapter.setTv(courses)
                movieAdapter.notifyDataSetChanged()
            })

            with(fragmentTvBinding.rvTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

}