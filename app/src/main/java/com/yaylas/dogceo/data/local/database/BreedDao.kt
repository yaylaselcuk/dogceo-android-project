package com.yaylas.dogceo.data.local.database

import androidx.room.*
import com.yaylas.dogceo.data.local.models.BreedCacheEntity

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreed(breedCacheEntity: BreedCacheEntity): Long

    @Query("select * from breeds where parent_breed is :parentBreed")
    suspend fun get(parentBreed: String?) : List<BreedCacheEntity>
}