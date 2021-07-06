package roemah.kreator.moviecat.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import roemah.kreator.moviecat.data.source.local.entity.MovieDb
import roemah.kreator.moviecat.data.source.local.entity.TvDb

@Database(entities = [MovieDb::class, TvDb::class],version = 1, exportSchema = false)
abstract class CatalogDb: RoomDatabase() {
    abstract fun catalogueDao(): CatalogueDao

    companion object{
        private var INSTANCE: CatalogDb? = null

        private val sLock = Any()

        fun getInstance(context: Context): CatalogDb {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CatalogDb::class.java, "catalogue.db"
                    )
                        .build()
                }
                return INSTANCE as CatalogDb
            }
        }
    }
}