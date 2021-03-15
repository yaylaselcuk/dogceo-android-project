package com.yaylas.dogceo.data.network

import com.yaylas.dogceo.data.network.models.ApiResponse

interface BreedRetrofitService {
    suspend fun getMasterBreeds(): ApiResponse

    suspend fun getBreedsByParent(parentBreed: String): ApiResponse

    suspend fun getMasterBreedImages(breed: String): ApiResponse

    suspend fun getSubBreedImages(breed: String, parentBreed: String): ApiResponse
}