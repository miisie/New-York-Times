package com.example.articleview.data

import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName("docs") val docs: MutableList<Docs>
)