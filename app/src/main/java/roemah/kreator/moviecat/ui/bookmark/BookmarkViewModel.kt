package roemah.kreator.moviecat.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roemah.kreator.moviecat.data.CourseEntity
import roemah.kreator.moviecat.data.source.AcademyRepository
import roemah.kreator.moviecat.utils.DataDummy

class BookmarkViewModel (private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()

}