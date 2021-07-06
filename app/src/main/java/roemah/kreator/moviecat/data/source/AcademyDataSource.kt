package roemah.kreator.moviecat.data.source

import androidx.lifecycle.LiveData
import roemah.kreator.moviecat.data.CourseEntity
import roemah.kreator.moviecat.data.ModuleEntity

interface AcademyDataSource {
//    fun getAllCourses(): List<CourseEntity>
//
//    fun getBookmarkedCourses(): List<CourseEntity>
//
//    fun getCourseWithModules(courseId: String): CourseEntity
//
//    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>
//
//    fun getContent(courseId: String, moduleId: String): ModuleEntity
    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>


}