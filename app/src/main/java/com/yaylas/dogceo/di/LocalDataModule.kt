package com.yaylas.dogceo.di

import android.content.Context
import androidx.room.Room
import com.yaylas.dogceo.data.local.database.BreedDao
import com.yaylas.dogceo.data.local.database.BreedDatabase
import com.yaylas.dogceo.data.local.database.ImagesDao
import com.yaylas.dogceo.data.local.datasource.LocalDataSource
import com.yaylas.dogceo.data.local.datasource.LocalDataSourceImpl
import com.yaylas.dogceo.data.local.mappers.BreedEntityMapper
import com.yaylas.dogceo.data.local.mappers.ImagesInfoEntityMapper
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.services.BreedDaoService
import com.yaylas.dogceo.data.local.services.BreedDaoServiceImpl
import com.yaylas.dogceo.data.local.services.ImagesDaoService
import com.yaylas.dogceo.data.local.services.ImagesDaoServiceImpl
import com.yaylas.dogceo.domain.EntityMapper
import com.yaylas.dogceo.domain.model.Breed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Singleton
    @Provides
    fun provideRoomEntityMapper(): EntityMapper<BreedCacheEntity, Breed> {
        return BreedEntityMapper()
    }

    @Singleton
    @Provides
    fun provideBreedDb(@ApplicationContext context: Context): BreedDatabase {
        return Room
            .databaseBuilder(
                context,
                BreedDatabase::class.java,
                BreedDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBreedDAO(breedDatabase: BreedDatabase): BreedDao {
        return breedDatabase.breedDao()
    }

    @Singleton
    @Provides
    fun provideImagesDAO(breedDatabase: BreedDatabase): ImagesDao {
        return breedDatabase.imagesDao()
    }

    @Singleton
    @Provides
    fun provideBreedDaoService(
        breedDao: BreedDao
    ): BreedDaoService {
        return BreedDaoServiceImpl(breedDao)
    }


    @Singleton
    @Provides
    fun provideImagesDaoService(
        imagesDao: ImagesDao
    ): ImagesDaoService {
        return ImagesDaoServiceImpl(imagesDao)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        breedDaoService: BreedDaoService,
        imagesDaoService: ImagesDaoService,
        imagesInfoEntityMapper: ImagesInfoEntityMapper,
        breedEntityMapper: BreedEntityMapper
    ): LocalDataSource {
        return LocalDataSourceImpl(
            breedDaoService,
            imagesDaoService,
            imagesInfoEntityMapper,
            breedEntityMapper
        )
    }

}

























