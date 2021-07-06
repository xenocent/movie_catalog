package roemah.kreator.moviecat.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.data.source.remote.CatalogDataSource
import roemah.kreator.moviecat.data.source.remote.CatalogRemoteDataSource
import roemah.kreator.moviecat.data.source.remote.response.MovieResponse
import roemah.kreator.moviecat.data.source.remote.response.TvResponse

class FakeCatalogRepository (private val remoteDataSource: CatalogRemoteDataSource) :
    CatalogDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val listMovieResult = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getNowPlayingMovies(object : CatalogRemoteDataSource.LoadNowPlayingMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<MovieEntity>()
                    for (response in movieResponse){
                        val movie = MovieEntity(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )
                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : CatalogRemoteDataSource.LoadMovieDetailCallback{
                override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                    val movie = MovieEntity(
                        movieResponse.id,
                        movieResponse.name,
                        movieResponse.desc,
                        movieResponse.poster,
                        movieResponse.img_preview
                    )

                    movieResult.postValue(movie)
                }
            })
        }

        return movieResult
    }

    override fun getAllTv(): LiveData<List<TvEntity>> {
        val listTvShowResult = MutableLiveData<List<TvEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowOnTheAir(object : CatalogRemoteDataSource.LoadOnTheAirTvShowCallback{
                override fun onAllTvShowsReceived(tvShowResponse: List<TvResponse>) {
                    val tvShowList = ArrayList<TvEntity>()
                    for (response in tvShowResponse){
                        val tvShow = TvEntity(
                            response.id,
                            response.name,
                            response.desc,
                            response.poster,
                            response.img_preview
                        )
                        tvShowList.add(tvShow)
                    }

                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getTvDetail(tvShowId: Int): LiveData<TvEntity> {
        val tvShowResult = MutableLiveData<TvEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId, object : CatalogRemoteDataSource.LoadTvShowDetailCallback{
                override fun onTvShowDetailReceived(tvShowResponse: TvResponse) {
                    val tvShow = TvEntity(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.desc,
                        tvShowResponse.poster,
                        tvShowResponse.img_preview
                    )

                    tvShowResult.postValue(tvShow)
                }
            })
        }

        return tvShowResult
    }
}