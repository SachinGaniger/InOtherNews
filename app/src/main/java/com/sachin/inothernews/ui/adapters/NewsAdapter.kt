package com.sachin.inothernews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin.inothernews.data.Article
import com.sachin.inothernews.databinding.ItemArticlePreviewBinding
import timber.log.Timber

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


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

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {

        val view = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val article = differ.currentList[position]
//        val article = newsArticles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            Timber.d("recycler item clicked!")
            onItemClickListener?.let { it(article) }
        }

    }

    override fun getItemCount(): Int {
//       return newsArticles.size
       return differ.currentList.size
    }


}