
package com.yaylas.dogceo.data.local.services

import com.yaylas.dogceo.data.local.database.BreedDao
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import javax.inject.Inject


class BreedDaoServiceImpl
@Inject constructor(
    private val breedDao: BreedDao
) : BreedDaoService {

    override suspend fun insertBreed(breedCacheEntity: BreedCacheEntity): Long {
        return breedDao.insertBreed(breedCacheEntity)
    }

    override suspend fun getBreeds(parentBreed: String?): List<BreedCacheEntity> {
        return breedDao.get(parentBreed)
    }

}