package org.plavelo.rss.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.plavelo.rss.databinding.FragmentFeedListItemBinding
import org.plavelo.rss.domain.Feed

class FeedListAdapter :
    ListAdapter<Feed, FeedListAdapter.FeedViewHolder>(FeedDiffCallback) {

    class FeedViewHolder(private val binding: FragmentFeedListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var currentFeed: Feed? = null

        fun bind(feed: Feed) {
            currentFeed = feed
            binding.textView.text = feed.url
        }
    }

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = FragmentFeedListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = getItem(position)
        holder.bind(feed)
    }
}

object FeedDiffCallback : DiffUtil.ItemCallback<Feed>() {
    override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem.url == newItem.url
    }
}
