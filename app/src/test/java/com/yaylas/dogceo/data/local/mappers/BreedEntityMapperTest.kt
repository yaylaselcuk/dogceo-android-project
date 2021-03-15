package com.yaylas.dogceo.data.local.mappers

import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.network.models.ApiResponse
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class BreedEntityMapperTest {

    private val breedEntityMapper = BreedEntityMapper()


    @Test
    fun mapFromEntity() {
        val breedCacheEntity = BreedCacheEntity("sub", "parent")
        val breedObj = breedEntityMapper.mapFromEntity(breedCacheEntity)
        assertEquals("sub", breedObj.name)
    }

}