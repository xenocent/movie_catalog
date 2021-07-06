package roemah.kreator.moviecat.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowmodel")
data class TvDb(
    @PrimaryKey
    @ColumnInfo(name="tvShowId")
    var id : Int? = 0,
    @ColumnInfo(name="tvShowTitle")
    val title: String? = "",
    @ColumnInfo(name="tvShowDescription")
    val overview: String? = "",
    @ColumnInfo(name="tvShowPoster")
    val poster : String?= "",
    var favorite: Boolean = false
)