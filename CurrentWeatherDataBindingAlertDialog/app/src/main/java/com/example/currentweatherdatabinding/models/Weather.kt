package com.example.currentweatherdatabinding.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    @SerializedName("description") val description: String?,

    @SerializedName("icon") val icon: String?
) : Parcelable