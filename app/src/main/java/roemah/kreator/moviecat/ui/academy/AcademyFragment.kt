package roemah.kreator.moviecat.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import roemah.kreator.moviecat.databinding.FragmentAcademyBinding
import roemah.kreator.moviecat.utils.DataDummy
import roemah.kreator.moviecat.viewmodel.ViewModelFactory

class AcademyFragment : Fragment() {
    private lateinit var fragmentAcademyBinding: FragmentAcademyBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAcademyBinding = FragmentAcademyBinding.inflate(layoutInflater, container, false)
        return fragmentAcademyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            // without factory
            // val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AcademyViewModel::class.java]
            // with view model factory
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]
            val courses = viewModel.getCourses()

            val academyAdapter = AcademyAdapter()

            fragmentAcademyBinding.progressBar.visibility = View.VISIBLE
            viewModel.getCourses().observe(this, { courses ->
                fragmentAcademyBinding.progressBar.visibility = View.GONE
                academyAdapter.setCourses(courses)
                academyAdapter.notifyDataSetChanged()
            })

            with(fragmentAcademyBinding.rvAcademy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}