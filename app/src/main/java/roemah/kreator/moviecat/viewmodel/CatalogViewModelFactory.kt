package roemah.kreator.moviecat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.di.CatalogInjection
import roemah.kreator.moviecat.ui.detail_catalog.DetailCatalogViewModel
import roemah.kreator.moviecat.ui.movie.MovieViewModel
import roemah.kreator.moviecat.ui.tv.TvViewModel

class CatalogViewModelFactory private constructor(private val mCatalogRepository: CatalogRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: CatalogViewModelFactory? = null

        fun getInstance(): CatalogViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: CatalogViewModelFactory(CatalogInjection.provideCatalogRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailCatalogViewModel::class.java) -> {
                DetailCatalogViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}
