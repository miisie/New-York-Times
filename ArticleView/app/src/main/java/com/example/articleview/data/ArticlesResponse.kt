package com.example.articleview.data

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("response") val respond: Response,
    @SerializedName("status") val status: String
)