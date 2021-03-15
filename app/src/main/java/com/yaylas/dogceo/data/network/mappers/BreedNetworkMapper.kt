package com.yaylas.dogceo.data.network.mappers

import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.network.models.ApiResponse
import com.yaylas.dogceo.domain.EntityMapper
import javax.inject.Inject

class BreedNetworkMapper  @Inject constructor() : EntityMapper<ApiResponse, List<BreedCacheEntity>> {

    override fun mapFromEntity(entity: ApiResponse): List<BreedCacheEntity> {
        val result = arrayListOf<BreedCacheEntity>()
        for (breed in entity.data) {
            result.add(BreedCacheEntity(breed, entity.parentBreed))
        }
        return result
    }

    override fun mapFromEntityList(entities: List<ApiResponse>): List<List<BreedCacheEntity>> {
        return entities.map { mapFromEntity(it) }
    }
}