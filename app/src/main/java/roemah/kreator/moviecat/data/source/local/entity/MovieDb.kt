package roemah.kreator.moviecat.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviemodel")
data class MovieDb(@PrimaryKey
                   @ColumnInfo(name="movieId")
                   var id : Int? = 0,
                   @ColumnInfo(name="movieTitle")
                   val title: String? = "",
                   @ColumnInfo(name="movieDescription")
                   val overview: String? = "",
                   @ColumnInfo(name="moviePoster")
                   val poster : String?= "",
                   var favorite: Boolean = false)