package org.plavelo.prs.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.plavelo.prs.R
import org.plavelo.prs.domain.Feed

class FeedListAdapter :
    ListAdapter<Feed, FeedListAdapter.FeedViewHolder>(FeedDiffCallback) {

    class FeedViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val urlTextView: TextView = itemView.findViewById(R.id.textView)
        private var currentFeed: Feed? = null

        fun bind(feed: Feed) {
            currentFeed = feed
            urlTextView.text = feed.url
        }
    }

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_feed_list_item, parent, false)
        return FeedViewHolder(view)
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
