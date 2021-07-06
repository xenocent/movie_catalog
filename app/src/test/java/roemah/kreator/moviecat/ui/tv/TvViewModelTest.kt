package roemah.kreator.moviecat.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import junit.framework.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.ui.movie.MovieViewModel
import roemah.kreator.moviecat.utils.DataDummy

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    private val dummyTvShow = DataDummy.generateDummyTv()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerTv: Observer<List<TvEntity>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(catalogRepository)
    }

    @Test
    fun getTvs() {
        val tvShow = MutableLiveData<List<TvEntity>>()
        tvShow.value = dummyTvShow

        `when`(catalogRepository.getAllTv()).thenReturn(tvShow)

        val dataListTvShow = viewModel.getTvs().value

        com.nhaarman.mockitokotlin2.verify(catalogRepository).getAllTv()
        assertNotNull(dataListTvShow)
        assertEquals(10, dataListTvShow?.size)

        viewModel.getTvs().observeForever(observerTv)
        com.nhaarman.mockitokotlin2.verify(observerTv).onChanged(dummyTvShow)
    }
}