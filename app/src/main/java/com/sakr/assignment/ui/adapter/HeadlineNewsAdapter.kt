package com.sakr.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sakr.assignment.data.models.Article
import com.sakr.assignment.databinding.ArticleItemBinding


class HeadlineNewsAdapter(
    private val articles: List<Article>,
    private val onClickListener: OnClickListener
) :
    ListAdapter<Article, HeadlineNewsAdapter.NewsViewHolder>(ArticleDiffCallBack()) {

    class NewsViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, onClickListener: OnClickListener) {
            binding.article = article
            binding.clickListener = onClickListener
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ArticleItemBinding.inflate(layoutInflater, parent, false)

                return NewsViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = articles[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class OnClickListener(val clickListener: (article: Article) -> Unit) {
        fun onClick(article: Article) = clickListener(article)
    }


    class ArticleDiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.content == newItem.content
        }

    }
}