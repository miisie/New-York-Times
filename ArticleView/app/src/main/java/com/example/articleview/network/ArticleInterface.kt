package com.example.articleview.network

import com.example.articleview.data.Docs


interface ArticleInterface {
    interface ArticleModel{
        fun GetArticles(page: Int)
        fun GetArticlesbyQuery(page: Int, query:String)
    }
    interface ArticleView{
        fun onSuccess(articles: MutableList<Docs>)
        fun onFailed(str: String)
    }
    interface ArticlePresenter{
        fun networkCall(page: Int)
        fun QueryCall(page: Int, query:String)
    }
}