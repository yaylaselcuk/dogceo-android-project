package com.yaylas.dogceo.data.local.services

import com.yaylas.dogceo.data.local.models.BreedCacheEntity

interface BreedDaoService {
    suspend fun insertBreed(breedCacheEntity: BreedCacheEntity): Long

    suspend fun getBreeds(parentBreed: String?): List<BreedCacheEntity>


}