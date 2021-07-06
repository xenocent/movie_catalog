package roemah.kreator.moviecat.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var id:Int,
    var title: String?,
    var overview: String?,
    var poster : String?,
    var image :String?,
):Parcelable
