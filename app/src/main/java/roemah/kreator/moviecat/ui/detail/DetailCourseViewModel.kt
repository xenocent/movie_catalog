package roemah.kreator.moviecat.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roemah.kreator.moviecat.data.CourseEntity
import roemah.kreator.moviecat.data.ModuleEntity
import roemah.kreator.moviecat.data.source.AcademyRepository
import roemah.kreator.moviecat.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

//    fun getCourse(): CourseEntity {
//        lateinit var course: CourseEntity
//        val coursesEntities = DataDummy.generateDummyCourses()
//        for (courseEntity in coursesEntities) {
//            if (courseEntity.courseId == courseId) {
//                course = courseEntity
//            }
//        }
//        return course
//    }

//    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}