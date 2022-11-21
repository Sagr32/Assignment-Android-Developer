package com.sakr.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sakr.assignment.data.models.Source
import com.sakr.assignment.databinding.SourceItemBinding


class SourceNewsAdapter(
    private val sources: List<Source>,
    private val onClickListener: OnSourceClicked
) :
    ListAdapter<Source, SourceNewsAdapter.NewsViewHolder>(SourceDiffCallBack()) {

    class NewsViewHolder(private val binding: SourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(source: Source, onClickListener: OnSourceClicked) {
            binding.source = source
            binding.onClick = onClickListener
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = SourceItemBinding.inflate(layoutInflater, parent, false)

                return NewsViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = sources[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return sources.size
    }


    class OnSourceClicked(val clickListener: (source: Source) -> Unit) {
        fun onClick(source: Source) = clickListener(source)
    }


    class SourceDiffCallBack : DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem.id == newItem.id
        }

    }
}