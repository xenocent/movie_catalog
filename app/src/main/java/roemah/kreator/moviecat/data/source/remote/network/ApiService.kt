package roemah.kreator.moviecat.data.source.remote.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import roemah.kreator.moviecat.BuildConfig
import roemah.kreator.moviecat.data.source.remote.response.ListResponse
import roemah.kreator.moviecat.data.source.remote.response.MovieResponse
import roemah.kreator.moviecat.data.source.remote.response.TvResponse

interface ApiService {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<ListResponse<MovieResponse>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getTvShowOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<ListResponse<TvResponse>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<TvResponse>
}