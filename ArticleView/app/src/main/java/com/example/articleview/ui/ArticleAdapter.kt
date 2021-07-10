package com.example.articleview.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.articleview.R
import com.example.articleview.data.Docs
import kotlinx.android.synthetic.main.article_item.view.*


class ArticleAdapter(private var articles: MutableList<Docs>): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }
    fun appendArticles(articles: MutableList<Docs>) {
        this.articles.addAll(articles)
        notifyItemRangeInserted(
            this.articles.size,
            articles.size - 1
        )
    }
    fun clear(){
        articles.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.articles.size
    }
    inner class ArticleViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.item_article_image)
        fun bind(article : Docs){
            itemView.item_article_title.text = article.headline.title
            if (!article.multimedia.isNullOrEmpty()) {
                Glide.with(itemView)
                    .load("http://static01.nyt.com/${article.multimedia[0].imgUrl}").error(R.drawable.ic_error)
                    .transform(CenterCrop())
                    .into(image)
                itemView.setOnClickListener{
                    val builder = CustomTabsIntent.Builder()
                    val customTabIntent = builder.build()
                    customTabIntent.launchUrl(itemView.context, Uri.parse(article.articleUrl))
                }
            }
        }
    }
}