package com.yaylas.dogceo.di

import com.yaylas.dogceo.data.local.datasource.LocalDataSource
import com.yaylas.dogceo.data.network.datasource.NetworkDataSource
import com.yaylas.dogceo.domain.interactors.GetMasterBreedImages
import com.yaylas.dogceo.domain.interactors.GetMasterBreeds
import com.yaylas.dogceo.domain.interactors.GetSubBreedImages
import com.yaylas.dogceo.domain.interactors.GetSubBreeds
import com.yaylas.dogceo.util.ConnectionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetMasterBreeds(
        connectionManager: ConnectionManager,
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): GetMasterBreeds {
        return GetMasterBreeds(connectionManager, localDataSource, networkDataSource)
    }

    @Singleton
    @Provides
    fun provideGetSubBreeds(
        connectionManager: ConnectionManager,
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): GetSubBreeds {
        return GetSubBreeds(connectionManager, localDataSource, networkDataSource)
    }

    @Singleton
    @Provides
    fun provideGetMasterBreedImages(
        connectionManager: ConnectionManager,
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): GetMasterBreedImages {
        return GetMasterBreedImages(connectionManager, localDataSource, networkDataSource)
    }


    @Singleton
    @Provides
    fun provideGetSubBreedImages(
        connectionManager: ConnectionManager,
        localDataSource: LocalDataSource,
        networkDataSource: NetworkDataSource
    ): GetSubBreedImages {
        return GetSubBreedImages(connectionManager, localDataSource, networkDataSource)
    }


}














