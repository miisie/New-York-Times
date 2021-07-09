package com.example.articleview.service

import android.util.Log
import com.example.articleview.data.ArticlesResponse
import com.example.articleview.data.Docs
import com.example.articleview.network.ApiClient
import com.example.articleview.network.ArticleInterface
import com.example.articleview.network.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction1

class ArticleRepos(private var onSuccess: (articles:MutableList<Docs>) -> Unit,
                   private var onError: (msg: String) -> Unit):  ArticleInterface.ArticleModel{
    private val api:ApiClient
    init {
        api = retrofit.client.create(ApiClient::class.java)
    }
    override fun GetArticles(Page: Int) {
        val query : String = "hero"
         api.getArticle(page=Page,q=query)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                           /* Log.d("Repository", "Article: ${responseBody.respond.docs[0].headline.title}")
                            Log.d("Repository", "Web_URL: ${responseBody.respond.docs[0].articleUrl}")
                            Log.d("Repository", "Article: ${responseBody.respond.docs[0].multimedia[0].imgUrl}")*/
                            onSuccess.invoke(responseBody.respond.docs)
                        } else {
                            onError.invoke(response.message())
                        }
                    }else{
                        onError.invoke(response.message())
                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    onError.invoke(t.message.toString())
                }
            })
    }

}