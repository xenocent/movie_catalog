package roemah.kreator.moviecat.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.utils.DataDummy

class TvViewModel(private val catalogRepository: CatalogRepository):ViewModel() {
    fun getTvs():LiveData<List<TvEntity>> = catalogRepository.getAllTv()
}