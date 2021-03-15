package com.yaylas.dogceo.data.local.mappers

import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import com.yaylas.dogceo.domain.EntityMapper
import com.yaylas.dogceo.domain.model.ImageInfo
import javax.inject.Inject


class ImagesInfoEntityMapper @Inject constructor() : EntityMapper<ImageCacheEntity, ImageInfo> {

    override fun mapFromEntity(entity: ImageCacheEntity): ImageInfo {
        return ImageInfo(
            url = entity.imageUrl
        )
    }

    override fun mapFromEntityList(entities: List<ImageCacheEntity>): List<ImageInfo> {
        return entities.map { mapFromEntity(it) }
    }
}

