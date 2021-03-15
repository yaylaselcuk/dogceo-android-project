package com.yaylas.dogceo.data.local.mappers

import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.domain.EntityMapper
import com.yaylas.dogceo.domain.model.Breed
import javax.inject.Inject

class BreedEntityMapper @Inject
constructor() : EntityMapper<BreedCacheEntity, Breed> {
    override fun mapFromEntity(entity: BreedCacheEntity): Breed {
        return Breed(
            name = entity.breedName
        )
    }

    override fun mapFromEntityList(entities: List<BreedCacheEntity>): List<Breed> {
        return entities.map { mapFromEntity(it) }
    }

}