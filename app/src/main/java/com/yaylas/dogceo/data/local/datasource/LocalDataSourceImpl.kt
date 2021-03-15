package com.yaylas.dogceo.data.local.datasource

import com.yaylas.dogceo.data.local.mappers.BreedEntityMapper
import com.yaylas.dogceo.data.local.mappers.ImagesInfoEntityMapper
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import com.yaylas.dogceo.data.local.services.BreedDaoService
import com.yaylas.dogceo.data.local.services.ImagesDaoService
import com.yaylas.dogceo.domain.model.Breed
import com.yaylas.dogceo.domain.model.ImageInfo
import javax.inject.Inject

class LocalDataSourceImpl
@Inject constructor(
    private val breedDaoService: BreedDaoService,
    private val imagesDaoService: ImagesDaoService,
    private val imagesInfoEntityMapper: ImagesInfoEntityMapper,
    private val breedEntityMapper: BreedEntityMapper
) : LocalDataSource {

    override suspend fun insertBreed(breedCacheEntity: BreedCacheEntity): Long {
        return breedDaoService.insertBreed(breedCacheEntity)
    }

    override suspend fun insertBreedList(breedCacheEntities: List<BreedCacheEntity>) {
        for (breedCacheEntity in breedCacheEntities) {
            breedDaoService.insertBreed(breedCacheEntity)
        }
    }

    override suspend fun getAllBreeds(parentBreed: String?): List<Breed> {
        return breedEntityMapper.mapFromEntityList(breedDaoService.getBreeds(parentBreed))
    }

    override suspend fun insertImage(imageCacheEntity: ImageCacheEntity): Long {
        return imagesDaoService.insertImage(imageCacheEntity)
    }

    override suspend fun insertImageList(imageCacheEntities: List<ImageCacheEntity>) {
        for (imageCacheEntity in imageCacheEntities) {
            imagesDaoService.insertImage(imageCacheEntity)
        }
    }

    override suspend fun getBreedImages(breedName: String, parentBreed: String?): List<ImageInfo> {
        return imagesInfoEntityMapper.mapFromEntityList(imagesDaoService.getImages(breedName, parentBreed))
    }
}