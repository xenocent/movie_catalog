package roemah.kreator.moviecat.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import roemah.kreator.moviecat.data.MovieEntity
import roemah.kreator.moviecat.data.TvEntity
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.ui.academy.AcademyViewModel
import roemah.kreator.moviecat.ui.tv.TvViewModel
import roemah.kreator.moviecat.utils.DataDummy
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    private val dummyMovie = DataDummy.generateDummyMovie()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<List<MovieEntity>>()
        movie.value = dummyMovie

        Mockito.`when`(catalogRepository.getAllMovies()).thenReturn(movie)

        val dataListMovie = viewModel.getMovies().value

        com.nhaarman.mockitokotlin2.verify(catalogRepository).getAllMovies()
        assertNotNull(dataListMovie)
        assertEquals(10, dataListMovie?.size)

        viewModel.getMovies().observeForever(observerMovie)
        com.nhaarman.mockitokotlin2.verify(observerMovie).onChanged(dummyMovie)
    }
}