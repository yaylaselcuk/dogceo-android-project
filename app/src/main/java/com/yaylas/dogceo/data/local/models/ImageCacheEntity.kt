package com.yaylas.dogceo.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images", primaryKeys = ["breed_name", "image_url"])
class ImageCacheEntity (

    @ColumnInfo(name = "breed_name")
    var breedName: String,

    @ColumnInfo(name = "parent_breed")
    var parentBreed: String?,

    @ColumnInfo(name = "image_url")
    var imageUrl: String

)