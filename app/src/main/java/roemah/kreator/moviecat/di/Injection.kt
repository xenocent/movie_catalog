package roemah.kreator.moviecat.di

import android.content.Context
import roemah.kreator.moviecat.data.source.AcademyRepository
import roemah.kreator.moviecat.data.source.remote.RemoteDataSource
import roemah.kreator.moviecat.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}