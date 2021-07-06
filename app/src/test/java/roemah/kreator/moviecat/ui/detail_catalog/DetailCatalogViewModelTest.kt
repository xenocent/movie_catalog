package roemah.kreator.moviecat.ui.detail_catalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.data.source.LiveDataTestUtil
import roemah.kreator.moviecat.data.source.remote.CatalogRemoteDataSource
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.data.source.remote.response.TvResponse
import roemah.kreator.moviecat.ui.detail.DetailCourseViewModel
import roemah.kreator.moviecat.utils.DataDummy
@RunWith(MockitoJUnitRunner::class)
class DetailCatalogViewModelTest {
    private lateinit var viewModel : DetailCatalogViewModel
    private val dummyDataMovie = DataDummy.generateDummyMovie()[0]
    private val dummyDataTv = DataDummy.generateDummyTv()[0]
    private val movieId = dummyDataMovie.id
    private val tvId = dummyDataMovie.id


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private val remote = mock(CatalogRemoteDataSource::class.java)

    @Mock
    private lateinit var observerTv: Observer<TvEntity>

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailCatalogViewModel(catalogRepository)
    }

    @Test
    fun getMovie() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyDataMovie

        Mockito.`when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movieDummy)
        viewModel.setSelectedItem(dummyDataMovie.id.toString())
        // val movieData = viewModel.getMovie().value as MovieEntity

        val movieData = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movieId))
        verify(catalogRepository).getMovieDetail(movieId)

        assertNotNull(movieData)
        assertEquals(dummyDataMovie.id, movieData.id)
        assertEquals(dummyDataMovie.title, movieData.title)
        assertEquals(dummyDataMovie.overview, movieData.overview)
        assertEquals(dummyDataMovie.poster, movieData.poster)
        assertEquals(dummyDataMovie.image, movieData.image)

        viewModel.getMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyDataMovie)
    }

    @Test
    fun getTv() {
        val tvShowDummy = MutableLiveData<TvEntity>()
        tvShowDummy.value = dummyDataTv

        Mockito.`when`(catalogRepository.getTvDetail(tvId)).thenReturn(tvShowDummy)
        viewModel.setSelectedItem(dummyDataMovie.id.toString())
        //  val tvData = viewModel.getTv().value as TvEntity
        val tvData = LiveDataTestUtil.getValue(catalogRepository.getTvDetail(tvId))
        verify(catalogRepository).getTvDetail(tvId)

        assertNotNull(tvData)
        assertEquals(dummyDataTv.id, tvData.id)
        assertEquals(dummyDataTv.title, tvData.title)
        assertEquals(dummyDataTv.overview, tvData.overview)
        assertEquals(dummyDataTv.poster, tvData.poster)
        assertEquals(dummyDataTv.image, tvData.image)

        viewModel.getTv().observeForever(observerTv)
        verify(observerTv).onChanged(dummyDataTv)
    }
}