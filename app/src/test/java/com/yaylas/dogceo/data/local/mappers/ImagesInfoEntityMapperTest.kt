package com.yaylas.dogceo.data.local.mappers

import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
import org.junit.Assert.*
import org.junit.Test

class ImagesInfoEntityMapperTest {
    private val imagesInfoEntityMapper = ImagesInfoEntityMapper()


    @Test
    fun mapFromEntity() {
        val imageCacheEntity = ImageCacheEntity("sub", "parent", "imageUrl")
        val imageObj = imagesInfoEntityMapper.mapFromEntity(imageCacheEntity)
        assertEquals("imageUrl", imageObj.url)
    }
}