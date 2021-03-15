package com.yaylas.dogceo.data.local.services

import com.yaylas.dogceo.data.local.database.ImagesDao
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import javax.inject.Inject

class ImagesDaoServiceImpl @Inject constructor(
    private val imagesDao: ImagesDao
) : ImagesDaoService {
    override suspend fun insertImage(imageCacheEntity: ImageCacheEntity): Long {
        return imagesDao.insertImage(imageCacheEntity)
    }

    override suspend fun getImages(breedName: String, parentBreed: String?): List<ImageCacheEntity> {
        return imagesDao.get(breedName, parentBreed)
    }
}