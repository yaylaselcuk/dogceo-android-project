package com.yaylas.dogceo.data.network

import com.yaylas.dogceo.data.network.models.ApiResponse
import com.yaylas.dogceo.data.network.retrofit.BreedRetrofit
import javax.inject.Inject

class BreedRetrofitServiceImpl @Inject constructor(private val breedRetrofit: BreedRetrofit) :
    BreedRetrofitService {

    override suspend fun getMasterBreeds(): ApiResponse {
        return breedRetrofit.getMasterBreeds()
    }

    override suspend fun getBreedsByParent(parentBreed: String): ApiResponse {
        return breedRetrofit.getMasterBreedsByParent(parentBreed)
    }

    override suspend fun getMasterBreedImages(breed: String): ApiResponse {
        return breedRetrofit.getMasterBreedImages(breed)
    }

    override suspend fun getSubBreedImages(breed: String, parentBreed: String): ApiResponse {
        return breedRetrofit.getSubBreedImages(breed, parentBreed)
    }
}