package com.example.articleview.network

import retrofit2.Call
import com.example.articleview.data.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("search/v2/articlesearch.json")
    fun getArticle(
        @Query("page") page: Int,
        @Query("q") q: String?,
        @Query("api-key") apiKey: String = "mqUtSDNyvshvMb2H3Ymku4GDlfrl0PGR"
    ): Call<ArticlesResponse>
}