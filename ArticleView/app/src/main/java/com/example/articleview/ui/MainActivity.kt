package com.example.articleview.ui

import com.example.articleview.ui.ArticleAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.articleview.R
import com.example.articleview.data.Docs
import com.example.articleview.network.ArticleInterface
import com.example.articleview.service.ArticlePresenter

class MainActivity : AppCompatActivity(),ArticleInterface.ArticleView,View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: ArticleAdapter
    private var page: Int = 1
    private lateinit var presenter:ArticlePresenter
    private lateinit var btnSearch: Button
    private lateinit var input: EditText
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    btnSearch = findViewById(R.id.btnSearch)
    input = findViewById(R.id.input)


    recyclerView = findViewById(R.id.article_list)
    layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

    recyclerView.layoutManager = layoutManager
    adapter = ArticleAdapter(mutableListOf())
    recyclerView.adapter = adapter


    presenter = ArticlePresenter(this)
    presenter.networkCall(page)

    }
     private fun onArticleFetched(articles: MutableList<Docs>) {
         adapter.appendArticles(articles)
         attachArticlesOnScrollListener()
     }

     private fun attachArticlesOnScrollListener() {
         recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
             override fun onScrolled(recyclerview: RecyclerView, dx: Int, dy: Int) {
                 val totalItemCount = layoutManager.itemCount
                 val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                 if (lastVisibleItem == totalItemCount-1) {
                     page++
                     if (presenter.check == false) {
                         presenter?.networkCall(page)
                     }else {
                         presenter?.QueryCall(page,query)

                     }
                 }
             }
         })
     }
     private fun onError() {
         Toast.makeText(this,"Error fetch article", Toast.LENGTH_SHORT).show()
     }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbarlayout, menu);
        return true;
    }

    override fun onSuccess(articles: MutableList<Docs>) {
        onArticleFetched(articles)
    }

    override fun onFailed(str: String) {
        onError()
    }

    override fun onClick(v: View?) {
        if(v?.getId() == R.id.btnSearch){
            presenter.check = true
            query = input.text.toString()
            presenter?.QueryCall(page,query)
        }
    }

}