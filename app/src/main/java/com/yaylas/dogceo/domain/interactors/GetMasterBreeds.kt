package com.yaylas.dogceo.domain.interactors

import com.yaylas.dogceo.data.local.datasource.LocalDataSource
import com.yaylas.dogceo.data.network.datasource.NetworkDataSource
import com.yaylas.dogceo.domain.DataState
import com.yaylas.dogceo.domain.model.Breed
import com.yaylas.dogceo.util.ConnectionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMasterBreeds @Inject
constructor(
    private val connectionManager: ConnectionManager,
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun execute(): Flow<DataState<List<Breed>>> = flow {
        emit(DataState.Loading)
        try {
            if (connectionManager.isConnected()) {
                val networkBreeds = networkDataSource.getMasterBreeds()
                localDataSource.insertBreedList(networkBreeds)
            }
            val data = localDataSource.getAllBreeds(null)
            emit(DataState.Success(data))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}