package com.yaylas.dogceo.data.network.datasource

import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import com.yaylas.dogceo.data.network.BreedRetrofitService
import com.yaylas.dogceo.data.network.exception.DogApiException
import com.yaylas.dogceo.data.network.mappers.BreedNetworkMapper
import com.yaylas.dogceo.data.network.mappers.ImagesNetworkMapper

class NetworkDataSourceImpl
constructor(
    private val breedRetrofitService: BreedRetrofitService,
    private val breedNetworkMapper: BreedNetworkMapper,
    private val imagesNetworkMapper: ImagesNetworkMapper
) : NetworkDataSource {


    override suspend fun getMasterBreeds(): List<BreedCacheEntity> {
        val response = breedRetrofitService.getMasterBreeds()
        response.error?.let {
            throw DogApiException(it)
        }
        return breedNetworkMapper.mapFromEntity(response)
    }

    override suspend fun getBreedsByParent(parentBreed: String): List<BreedCacheEntity> {
        val response = breedRetrofitService.getBreedsByParent(parentBreed)
        response.error?.let {
            throw DogApiException(it)
        }
        response.parentBreed = parentBreed
        return breedNetworkMapper.mapFromEntity(response)
    }

    override suspend fun getMasterBreedImages(breed: String): List<ImageCacheEntity> {
        val response = breedRetrofitService.getMasterBreedImages(breed)
        response.error?.let {
            throw DogApiException(it)
        }
        response.breed = breed
        return imagesNetworkMapper.mapFromEntity(response)
    }

    override suspend fun getSubBreedImages(
        breed: String,
        parentBreed: String
    ): List<ImageCacheEntity> {
        val response = breedRetrofitService.getSubBreedImages(breed, parentBreed)
        response.error?.let {
            throw DogApiException(it)
        }
        response.breed = breed
        response.parentBreed = parentBreed
        return imagesNetworkMapper.mapFromEntity(response)
    }

}