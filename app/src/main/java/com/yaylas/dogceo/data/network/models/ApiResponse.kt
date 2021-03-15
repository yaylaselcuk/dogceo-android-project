package com.yaylas.dogceo.data.network.models

data class ApiResponse(
    var data : List<String>,
    var error : String?,
    var breed : String?,
    var parentBreed : String?
)