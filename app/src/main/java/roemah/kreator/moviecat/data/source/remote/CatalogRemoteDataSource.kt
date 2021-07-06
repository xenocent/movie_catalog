package roemah.kreator.moviecat.data.source.remote

import retrofit2.await
import roemah.kreator.moviecat.data.source.remote.network.RetrofitBuilder
import roemah.kreator.moviecat.data.source.remote.response.MovieResponse
import roemah.kreator.moviecat.data.source.remote.response.TvResponse
import roemah.kreator.moviecat.utils.EspressoIdlingResource

class CatalogRemoteDataSource {
    companion object {
        @Volatile
        private var instance: CatalogRemoteDataSource? = null

        fun getInstance(): CatalogRemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: CatalogRemoteDataSource()
            }
    }

    suspend fun getNowPlayingMovies(
        callback: LoadNowPlayingMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        RetrofitBuilder.instance.getNowPlayingMovies().await().result?.let { listMovie ->
            callback.onAllMoviesReceived(
                listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        RetrofitBuilder.instance.getDetailMovie(movieId).await().let { movie ->
            callback.onMovieDetailReceived(
                movie
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowOnTheAir(callback: LoadOnTheAirTvShowCallback) {
        EspressoIdlingResource.increment()
        RetrofitBuilder.instance.getTvShowOnTheAir().await().result?.let { listTvShow ->
            callback.onAllTvShowsReceived(
                listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        RetrofitBuilder.instance.getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.onTvShowDetailReceived(
                tvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadNowPlayingMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    interface LoadOnTheAirTvShowCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvResponse>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvResponse)
    }
}