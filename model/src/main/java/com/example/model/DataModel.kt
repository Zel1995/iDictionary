package com.example.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<Meanings>?
):Parcelable