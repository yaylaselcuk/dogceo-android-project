package com.yaylas.dogceo.domain.interactors

import com.yaylas.dogceo.data.local.datasource.LocalDataSource
import com.yaylas.dogceo.data.network.datasource.NetworkDataSource
import com.yaylas.dogceo.domain.DataState
import com.yaylas.dogceo.domain.model.Breed
import com.yaylas.dogceo.domain.model.ImageInfo
import com.yaylas.dogceo.util.ConnectionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMasterBreedImages @Inject
constructor(
    private val connectionManager: ConnectionManager,
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun execute(breed: String): Flow<DataState<List<ImageInfo>>> = flow {
        emit(DataState.Loading)
        try {
            if (connectionManager.isConnected()) {
                val networkImages = networkDataSource.getMasterBreedImages(breed)
                localDataSource.insertImageList(networkImages)
            }
            val data = localDataSource.getBreedImages(breed, null)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}