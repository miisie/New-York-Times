package com.example.articleview.service

import androidx.recyclerview.widget.RecyclerView
import com.example.articleview.data.Docs
import com.example.articleview.network.ArticleInterface
import com.example.articleview.ui.ArticleAdapter
private lateinit var ArticlesAdapter: ArticleAdapter
private lateinit var ArticleRecyclerView: RecyclerView

class ArticlePresenter(articleView: ArticleInterface.ArticleView): ArticleInterface.ArticlePresenter {
    private val model: ArticleInterface.ArticleModel = ArticleRepos(this::onSuccess, this::onError)
    private var view: ArticleInterface.ArticleView = articleView
    override fun networkCall(page: Int) {
        model?.GetArticles(page)
    }
    /* override fun onArticleFetched(articles: MutableList<Docs>) {
         ArticlesAdapter.appendArticles(articles)
         attachArticlesOnScrollListener()
     }

     override fun attachArticlesOnScrollListener() {
         ArticleRecyclerView
     }
     override fun onError() {

         Toast.makeText(this,"Error fetch article", Toast.LENGTH_SHORT).show()
     }*/
    private fun onSuccess(articles: MutableList<Docs>) {
        view.onSuccess(articles)
    }

    private fun onError(str: String) {
        view.onFailed(str)
    }
}