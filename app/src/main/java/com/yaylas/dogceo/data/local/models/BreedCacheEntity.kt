package com.yaylas.dogceo.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
class BreedCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "breed_name")
    var breedName: String,

    @ColumnInfo(name = "parent_breed")
    var parentBreed: String?

)


