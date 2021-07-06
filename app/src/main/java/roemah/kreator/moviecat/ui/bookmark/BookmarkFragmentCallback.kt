package roemah.kreator.moviecat.ui.bookmark

import roemah.kreator.moviecat.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
