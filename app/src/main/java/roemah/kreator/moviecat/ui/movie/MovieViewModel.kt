package roemah.kreator.moviecat.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.utils.DataDummy

class MovieViewModel(private val catalogRepository: CatalogRepository):ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = catalogRepository.getAllMovies()
}