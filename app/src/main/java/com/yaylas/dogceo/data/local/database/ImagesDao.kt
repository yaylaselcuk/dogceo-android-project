package com.yaylas.dogceo.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageCacheEntity: ImageCacheEntity): Long

    @Query("select * from images where breed_name is :breed and parent_breed is :parentBreed")
    suspend fun get(breed: String, parentBreed: String?) : List<ImageCacheEntity>
}