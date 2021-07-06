package roemah.kreator.moviecat.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roemah.kreator.moviecat.data.CourseEntity
import roemah.kreator.moviecat.data.source.AcademyRepository
import roemah.kreator.moviecat.utils.DataDummy

class AcademyViewModel (private val academyRepository: AcademyRepository): ViewModel() {
    //    old method tanpa repository
    //    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
    //    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()

}
