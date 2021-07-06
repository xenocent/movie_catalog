package roemah.kreator.moviecat.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import roemah.kreator.moviecat.data.source.local.entity.MovieDb
import roemah.kreator.moviecat.data.source.local.entity.TvDb
import roemah.kreator.moviecat.data.source.local.room.CatalogueDao

class LocalRepository constructor(private val catalogueDao: CatalogueDao) {
    companion object {

        private var INSTANCE: LocalRepository? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalRepository {
            if (INSTANCE == null) {
                INSTANCE =
                    LocalRepository(catalogueDao)
            }
            return INSTANCE as LocalRepository
        }
    }

    fun getMovies(): LiveData<List<MovieDb>> = catalogueDao.getMovies()

    fun getMovieById(movieId: Int): LiveData<MovieDb> = catalogueDao.getMovieById(movieId)

    fun insertMovies(movies:List<MovieDb>){
        catalogueDao.insertMovies(movies)
    }

    fun setFavoriteMovie(movie: MovieDb, isFavorite:Boolean){
        movie.favorite = isFavorite
        catalogueDao.updateMovie(movie)
    }

    fun getMovieAsPaged(): DataSource.Factory<Int, MovieDb> {
        return catalogueDao.getMovieAsPaged()
    }


    fun getTvShows(): LiveData<List<TvDb>> = catalogueDao.getTvShows()

    fun getTvShowById(tvShowId: Int): LiveData<TvDb> = catalogueDao.getTvShowById(tvShowId)

    fun insertTvShows(tvShows:List<TvDb>){
        catalogueDao.insertTvShow(tvShows)
    }

    fun setFavoriteTvShow(tvShow: TvDb, isFavorite:Boolean){
        tvShow.favorite = isFavorite
        catalogueDao.updateTvShow(tvShow)
    }

    fun getTvShowAsPaged(): DataSource.Factory<Int, TvDb> {
        return catalogueDao.getTvShowAsPaged()
    }

}