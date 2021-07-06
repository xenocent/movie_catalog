package roemah.kreator.moviecat.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import roemah.kreator.moviecat.data.source.local.entity.MovieDb
import roemah.kreator.moviecat.data.source.local.entity.TvDb

@Dao
interface CatalogueDao {
    @Transaction
    @Query("SELECT * FROM moviemodel WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieDb>): LongArray

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(movie: MovieDb): Int

    @WorkerThread
    @Query("SELECT * FROM moviemodel where favorite = 1")
    fun getMovies(): LiveData<List<MovieDb>>

    @Query("SELECT * FROM moviemodel where favorite = 1")
    fun getMovieAsPaged(): DataSource.Factory<Int, MovieDb>

    @Transaction
    @Query("SELECT * FROM tvshowmodel WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<TvDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvDb>): LongArray

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShow(tvShow: TvDb): Int

    @WorkerThread
    @Query("SELECT * FROM tvshowmodel where favorite = 1")
    fun getTvShows(): LiveData<List<TvDb>>

    @Query("SELECT * FROM tvshowmodel where favorite = 1")
    fun getTvShowAsPaged(): DataSource.Factory<Int, TvDb>
}