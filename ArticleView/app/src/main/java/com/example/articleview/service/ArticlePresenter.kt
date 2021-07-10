package com.example.articleview.service

import com.example.articleview.data.Docs
import com.example.articleview.network.ArticleInterface

class ArticlePresenter(articleView: ArticleInterface.ArticleView): ArticleInterface.ArticlePresenter {
    private val model: ArticleInterface.ArticleModel = ArticleRepos(this::onSuccess, this::onError)
    private var view: ArticleInterface.ArticleView = articleView
    var check: Boolean = false
    override fun networkCall(page: Int) {
        check = false
        model?.GetArticles(page)
    }

    override fun QueryCall(page: Int, query: String) {
        check = true
        model?.GetArticlesbyQuery(page,query)
    }

    private fun onSuccess(articles: MutableList<Docs>) {
        view.onSuccess(articles)
    }

    private fun onError(str: String) {
        view.onFailed(str)
    }
}