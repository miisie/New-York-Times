package com.example.articleview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.articleview.R
import com.example.articleview.data.Docs
import com.example.articleview.network.ArticleInterface
import com.example.articleview.service.ArticlePresenter

class MainActivity : AppCompatActivity(),ArticleInterface.ArticleView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: ArticleAdapter
    private var page: Int = 1
    private var presenter:ArticlePresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    recyclerView = findViewById(R.id.article_list)
    layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
    recyclerView.layoutManager = layoutManager
    adapter = ArticleAdapter(mutableListOf())
    recyclerView.adapter = adapter

    presenter = ArticlePresenter(this)
    presenter!!.networkCall(page)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbarlayout, menu);
        return true;
    }

    override fun onSuccess(articles: MutableList<Docs>) {
        TODO("Not yet implemented")
    }

    override fun onFailed(msg: String) {
        TODO("Not yet implemented")
    }

}