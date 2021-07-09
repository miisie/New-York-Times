package com.example.articleview.data

import com.google.gson.annotations.SerializedName

data class Docs(
    @SerializedName("headline") val headline: Headline,
    @SerializedName("web_url") val articleUrl: String,
    @SerializedName("multimedia") val multimedia: List<MultiMedia>,
    @SerializedName("print_page") val page: Int
)
