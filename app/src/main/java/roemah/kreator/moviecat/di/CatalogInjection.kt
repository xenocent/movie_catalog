package roemah.kreator.moviecat.di

import roemah.kreator.moviecat.data.source.remote.CatalogRemoteDataSource
import roemah.kreator.moviecat.data.source.remote.CatalogRepository

object CatalogInjection {
    fun provideCatalogRepository(): CatalogRepository {
        val remoteDataSource = CatalogRemoteDataSource.getInstance()
        return CatalogRepository.getInstance(remoteDataSource)
    }
}