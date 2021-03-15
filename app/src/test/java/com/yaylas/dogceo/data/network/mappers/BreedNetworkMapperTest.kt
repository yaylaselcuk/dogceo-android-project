package com.yaylas.dogceo.data.network.mappers

import com.yaylas.dogceo.data.network.models.ApiResponse
import org.junit.Test

import org.junit.Assert.*

class BreedNetworkMapperTest {

    private val breedNetworkMapper = BreedNetworkMapper()

    @Test
    fun mapFromEntity() {
        val apiResponse = ApiResponse(listOf("a", "b", "c", "d"), "", null, "master")
        val breedObjects = breedNetworkMapper.mapFromEntity(apiResponse)
        assertEquals(4, breedObjects.size)
        assertEquals("b", breedObjects[1].breedName)
        assertEquals("master", breedObjects[2].parentBreed)
    }

}