package com.sachin.inothernews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin.inothernews.data.Article
import com.sachin.inothernews.databinding.ItemArticlePreviewBinding

class NewsAdapter(
    private val newsArticles : List<Article>
): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(private val articlePreviewBinding: ItemArticlePreviewBinding) : RecyclerView.ViewHolder(articlePreviewBinding.root ) {
        fun bind(article: Article){
            articlePreviewBinding.apply {
                tvTitle.text = article.title
                tvDescription.text = article.description

                Glide.with(this.root).load(article.urlToImage)
                    .into(ivArticleImage)

                tvSource.text = article.source.name
                tvPublishedAt.text = article.publishedAt

            }
        }
    }

    private var onItemClickListener : ((Article) -> Unit)?= null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {

        val view = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val article = newsArticles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(article) }
        }

    }

    override fun getItemCount(): Int {
       return newsArticles.size
    }


}