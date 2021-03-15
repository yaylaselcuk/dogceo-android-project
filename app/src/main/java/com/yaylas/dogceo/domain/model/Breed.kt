package com.yaylas.dogceo.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Breed(
    var name: String
) : Parcelable