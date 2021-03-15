package com.yaylas.dogceo.data.network.retrofit

import com.yaylas.dogceo.data.network.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreedRetrofit {

    @GET("breeds/list")
    suspend fun getMasterBreeds(): ApiResponse

    @GET("breed/{parentBreed}/list")
    suspend fun getMasterBreedsByParent(@Path("parentBreed") parentBreed : String): ApiResponse

    @GET("breed/{breed}/images")
    suspend fun getMasterBreedImages(@Path("breed") breed : String): ApiResponse

    @GET("breed/{parentBreed}/{breed}/images")
    suspend fun getSubBreedImages(@Path("breed") breed : String, @Path("parentBreed") parentBreed : String): ApiResponse
}