package com.example.articleview.network

import com.example.articleview.data.Docs


interface ArticleInterface {
    interface ArticleModel{
        fun GetArticles(page: Int)
    }
    interface ArticleView{
        fun onSuccess(articles: MutableList<Docs>)
        fun onFailed(str: String)
    }
    interface ArticlePresenter{
        fun networkCall(page: Int)
        /*fun onArticleFetched(articles: MutableList<Docs>)
        fun attachArticlesOnScrollListener()
        fun onError()*/
    }
}