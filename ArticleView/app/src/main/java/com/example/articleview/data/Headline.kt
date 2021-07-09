package com.example.articleview.data

import com.google.gson.annotations.SerializedName

data class Headline (
    @SerializedName("main") val title: String
)