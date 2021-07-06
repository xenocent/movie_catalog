package roemah.kreator.moviecat.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import roemah.kreator.moviecat.data.source.FakeCatalogRepository
import roemah.kreator.moviecat.data.source.LiveDataTestUtil
import roemah.kreator.moviecat.utils.DataDummy

class CatalogRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(CatalogRemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote)

    private val listMovieResponse = DataDummy.generateDataMovieDummyResponse()
    private val movieId = listMovieResponse[0].id
    private val listTvShowResponse = DataDummy.generateDataTvShowDummyResponse()
    private val tvShowId = listTvShowResponse[0].id
    private val movieResponse = DataDummy.generateDataMovieDummyResponse()[0]
    private val tvShowResponse = DataDummy.generateDataTvShowDummyResponse()[0]

    @Test
    fun getAllMovies() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[0] as CatalogRemoteDataSource.LoadNowPlayingMoviesCallback).onAllMoviesReceived(listMovieResponse)
                null
            }.`when`(remote).getNowPlayingMovies(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getAllMovies())

        runBlocking {
            verify(remote).getNowPlayingMovies(any())
        }

        assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as CatalogRemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(movieResponse)
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        assertNotNull(data)
        assertEquals(movieResponse.id, data.id)
    }

    @Test
    fun getAllTv() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as CatalogRemoteDataSource.LoadOnTheAirTvShowCallback).onAllTvShowsReceived(listTvShowResponse)
                null
            }.`when`(remote).getTvShowOnTheAir(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getAllTv())

        runBlocking {
            verify(remote).getTvShowOnTheAir(any())
        }

        assertNotNull(data)
        assertEquals(listTvShowResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getTvDetail() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as CatalogRemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(tvShowResponse)
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getTvDetail(tvShowId))

        runBlocking {
            verify(remote).getTvShowDetail(eq(tvShowId), any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponse.id, data.id)
    }
}