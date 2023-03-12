package com.example.currentweatherdatabinding.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    @SerializedName("temp") val temp: String?,

    @SerializedName("feels_like") val feels_like: String?,

    @SerializedName("humidity") val humidity: String?,
) : Parcelable