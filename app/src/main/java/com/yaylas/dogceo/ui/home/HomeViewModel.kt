package com.yaylas.dogceo.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaylas.dogceo.domain.DataState
import com.yaylas.dogceo.domain.interactors.GetMasterBreeds
import com.yaylas.dogceo.domain.model.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMasterBreeds: GetMasterBreeds
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Breed>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Breed>>>
        get() = _dataState

    fun setStateEvent(stateEvent: HomeStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is HomeStateEvent.GetMasterBreedsEvent -> {
                    getMasterBreeds.execute().onEach { ds ->
                        _dataState.value = ds
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class HomeStateEvent {
    object GetMasterBreedsEvent : HomeStateEvent()
}