package com.yaylas.dogceo.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageInfo(
    var url: String
) : Parcelable