package com.yaylas.dogceo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import com.yaylas.dogceo.data.network.ApiResponseDeserializer
import com.yaylas.dogceo.data.network.BreedRetrofitService
import com.yaylas.dogceo.data.network.BreedRetrofitServiceImpl
import com.yaylas.dogceo.data.network.datasource.NetworkDataSource
import com.yaylas.dogceo.data.network.datasource.NetworkDataSourceImpl
import com.yaylas.dogceo.data.network.mappers.BreedNetworkMapper
import com.yaylas.dogceo.data.network.mappers.ImagesNetworkMapper
import com.yaylas.dogceo.data.network.models.ApiResponse
import com.yaylas.dogceo.data.network.retrofit.BreedRetrofit
import com.yaylas.dogceo.domain.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val API_BASE_URL = "https://dog.ceo/api/"

    @Singleton
    @Provides
    fun provideBreedNetworkMapper(): EntityMapper<ApiResponse, List<BreedCacheEntity>> {
        return BreedNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideImagesNetworkMapper(): EntityMapper<ApiResponse, List<ImageCacheEntity>> {
        return ImagesNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .registerTypeAdapter(ApiResponse::class.java, ApiResponseDeserializer())
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBreedService(retrofit: Retrofit.Builder): BreedRetrofit {
        return retrofit
            .build()
            .create(BreedRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        breedRetrofit: BreedRetrofit
    ): BreedRetrofitService {
        return BreedRetrofitServiceImpl(breedRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        breedRetrofitService: BreedRetrofitService,
        breedNetworkMapper: BreedNetworkMapper,
        imagesNetworkMapper: ImagesNetworkMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(breedRetrofitService, breedNetworkMapper, imagesNetworkMapper)
    }

}




















