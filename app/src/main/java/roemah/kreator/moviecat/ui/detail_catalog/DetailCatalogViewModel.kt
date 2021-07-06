package roemah.kreator.moviecat.ui.detail_catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.utils.DataDummy

class DetailCatalogViewModel(private val catalogRepository: CatalogRepository) :ViewModel(){
    private lateinit var itemId:String

    fun setSelectedItem(itemId:String){
        this.itemId = itemId
    }

    fun getMovie():LiveData<MovieEntity> = catalogRepository.getMovieDetail(this.itemId.toInt())

    fun getTv():LiveData<TvEntity> =  catalogRepository.getTvDetail(this.itemId.toInt())
}