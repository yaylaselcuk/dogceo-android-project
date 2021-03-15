package com.yaylas.dogceo.data.local.datasource

import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import com.yaylas.dogceo.domain.model.Breed
import com.yaylas.dogceo.domain.model.ImageInfo

interface LocalDataSource {
    suspend fun insertBreed(breedCacheEntity: BreedCacheEntity): Long

    suspend fun insertBreedList(breedCacheEntities: List<BreedCacheEntity>)

    suspend fun getAllBreeds(parentBreed: String?): List<Breed>

    suspend fun insertImage(imageCacheEntity: ImageCacheEntity): Long

    suspend fun insertImageList(imageCacheEntities: List<ImageCacheEntity>)

    suspend fun getBreedImages(breedName: String, parentBreed: String?): List<ImageInfo>
}