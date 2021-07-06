package roemah.kreator.moviecat.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roemah.kreator.moviecat.data.source.AcademyRepository
import roemah.kreator.moviecat.data.source.remote.CatalogRepository
import roemah.kreator.moviecat.di.Injection
import roemah.kreator.moviecat.ui.academy.AcademyViewModel
import roemah.kreator.moviecat.ui.bookmark.BookmarkViewModel
import roemah.kreator.moviecat.ui.detail.DetailCourseViewModel
import roemah.kreator.moviecat.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                return AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                return DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                return BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                return CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}