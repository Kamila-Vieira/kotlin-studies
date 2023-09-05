package br.com.studyng.alugames.api

import com.google.gson.annotations.SerializedName

data class InfoApiShark(
    @SerializedName("title") val title: String,
    @SerializedName("thumb") val cover: String
)

