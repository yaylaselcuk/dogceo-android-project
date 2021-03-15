package com.yaylas.dogceo.data.network.datasource

import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import com.yaylas.dogceo.data.network.models.ApiResponse

interface NetworkDataSource {

    suspend fun getMasterBreeds(): List<BreedCacheEntity>

    suspend fun getBreedsByParent(parentBreed: String): List<BreedCacheEntity>

    suspend fun getMasterBreedImages(breed: String): List<ImageCacheEntity>

    suspend fun getSubBreedImages(breed: String, parentBreed: String): List<ImageCacheEntity>

}