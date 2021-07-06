package roemah.kreator.moviecat.data.source.remote

import androidx.lifecycle.LiveData
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.TvEntity

interface CatalogDataSource {
    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getAllTv(): LiveData<List<TvEntity>>

    fun getTvDetail(tvShowId: Int): LiveData<TvEntity>

}