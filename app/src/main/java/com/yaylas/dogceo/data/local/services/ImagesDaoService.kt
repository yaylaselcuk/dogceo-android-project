package com.yaylas.dogceo.data.local.services

import com.yaylas.dogceo.data.local.models.ImageCacheEntity

interface ImagesDaoService {
    suspend fun insertImage(imageCacheEntity: ImageCacheEntity): Long

    suspend fun getImages(breedName: String, parentBreed: String?): List<ImageCacheEntity>
}