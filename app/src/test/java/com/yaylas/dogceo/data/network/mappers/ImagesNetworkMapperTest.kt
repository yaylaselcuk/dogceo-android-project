package com.yaylas.dogceo.data.network.mappers

import com.yaylas.dogceo.data.network.models.ApiResponse
import org.junit.Test

import org.junit.Assert.*

class ImagesNetworkMapperTest {

    private val imagesNetworkMapper = ImagesNetworkMapper()

    @Test
    fun mapFromEntity() {
        val apiResponse = ApiResponse(listOf("a", "b", "c", "d"), "", "sub", "master")
        val imageObjects = imagesNetworkMapper.mapFromEntity(apiResponse)
        assertEquals(4, imageObjects.size)
        assertEquals("b", imageObjects[1].imageUrl)
        assertEquals("sub", imageObjects[2].breedName)
        assertEquals("master", imageObjects[2].parentBreed)
    }
}