package com.example.currentweatherdatabinding.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    @SerializedName("speed") val speed: String?,
) : Parcelable