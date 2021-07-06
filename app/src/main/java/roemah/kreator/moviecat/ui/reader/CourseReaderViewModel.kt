package roemah.kreator.moviecat.ui.reader

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roemah.kreator.moviecat.data.ContentEntity
import roemah.kreator.moviecat.data.ModuleEntity
import roemah.kreator.moviecat.data.source.AcademyRepository
import roemah.kreator.moviecat.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        Log.d("testing",courseId + " masuk")
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): LiveData<List<ModuleEntity>> =
        academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): LiveData<ModuleEntity> =
        academyRepository.getContent(courseId, moduleId)

}