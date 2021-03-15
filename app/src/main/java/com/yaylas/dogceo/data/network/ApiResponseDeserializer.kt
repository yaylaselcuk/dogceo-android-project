package com.yaylas.dogceo.data.network

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.yaylas.dogceo.data.network.models.ApiResponse
import com.yaylas.dogceo.data.network.models.ApiResponseDTO
import java.lang.ClassCastException
import java.lang.reflect.Type


class ApiResponseDeserializer : JsonDeserializer<ApiResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ApiResponse {
        val response = ApiResponse(data = emptyList(), error = null, breed = null, parentBreed = null)
        if (json == null || json is JsonArray) {
            response.error = "Can't find the response!"
            return response
        }
        try {
            val responseDTO = Gson().fromJson(json, ApiResponseDTO::class.java)
            if (responseDTO.status != SUCCESS) {
                response.error = responseDTO.message.toString()
            } else {

                response.data = responseDTO.message as List<String>
            }
        } catch (e : ClassCastException) {
            response.error = "Response is corrupted!"
        }

        return response
    }

    companion object {
        private const val SUCCESS = "success"
    }
}