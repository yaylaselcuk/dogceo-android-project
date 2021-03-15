package com.yaylas.dogceo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity

@Database(entities = [BreedCacheEntity::class, ImageCacheEntity::class], version = 1)
abstract class BreedDatabase : RoomDatabase() {

    abstract fun breedDao(): BreedDao

    abstract fun imagesDao(): ImagesDao

    companion object {
        const val DATABASE_NAME: String = "breeds_db"
    }


}