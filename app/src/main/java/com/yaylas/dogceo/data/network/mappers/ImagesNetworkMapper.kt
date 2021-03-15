package com.yaylas.dogceo.data.network.mappers

import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import com.yaylas.dogceo.data.network.models.ApiResponse
import com.yaylas.dogceo.domain.EntityMapper
import javax.inject.Inject

class ImagesNetworkMapper @Inject constructor() :
    EntityMapper<ApiResponse, List<ImageCacheEntity>> {

    override fun mapFromEntity(entity: ApiResponse): List<ImageCacheEntity> {
        val result = arrayListOf<ImageCacheEntity>()
        for (url in entity.data) {
            result.add(
                ImageCacheEntity(
                    breedName = entity.breed ?: "",
                    parentBreed = entity.parentBreed,
                    imageUrl = url
                )
            )
        }
        return result
    }

    override fun mapFromEntityList(entities: List<ApiResponse>): List<List<ImageCacheEntity>> {
        return entities.map { mapFromEntity(it) }
    }
}