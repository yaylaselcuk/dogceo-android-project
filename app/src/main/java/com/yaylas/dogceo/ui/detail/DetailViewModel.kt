package com.yaylas.dogceo.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaylas.dogceo.domain.DataState
import com.yaylas.dogceo.domain.interactors.GetMasterBreedImages
import com.yaylas.dogceo.domain.interactors.GetSubBreedImages
import com.yaylas.dogceo.domain.interactors.GetSubBreeds
import com.yaylas.dogceo.domain.model.Breed
import com.yaylas.dogceo.domain.model.ImageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSubBreeds: GetSubBreeds,
    private val getMasterBreedImages: GetMasterBreedImages,
    private val getSubBreedImages: GetSubBreedImages
) : ViewModel() {

    lateinit var parentBreed: String

    var breed: String? = null

    private val _breedsDataState: MutableLiveData<DataState<List<Breed>>> = MutableLiveData()

    val breedsDataState: LiveData<DataState<List<Breed>>>
        get() = _breedsDataState

    private val _imageInfoDataState: MutableLiveData<DataState<List<ImageInfo>>> = MutableLiveData()

    val imageInfoDataState: LiveData<DataState<List<ImageInfo>>>
        get() = _imageInfoDataState

    fun setStateEvent(stateEvent: DetailStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is DetailStateEvent.GetSubBreedsEvent -> {
                    if (breed == null) {
                        getSubBreeds.execute(parentBreed).onEach { ds ->
                            _breedsDataState.value = ds
                        }.launchIn(viewModelScope)
                    } else {
                        setStateEvent(DetailStateEvent.GetImagesEvent)
                    }

                }
                is DetailStateEvent.GetImagesEvent -> {
                    if(breed == null) {
                        getMasterBreedImages.execute(parentBreed).onEach { ds ->
                            _imageInfoDataState.value = ds
                        }.launchIn(viewModelScope)

                    } else {
                        getSubBreedImages.execute(breed!!, parentBreed).onEach { ds ->
                            _imageInfoDataState.value = ds
                        }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }

}

sealed class DetailStateEvent {
    object GetSubBreedsEvent : DetailStateEvent()
    object GetImagesEvent : DetailStateEvent()
}