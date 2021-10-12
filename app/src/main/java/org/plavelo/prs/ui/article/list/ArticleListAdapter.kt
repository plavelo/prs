package org.plavelo.prs.ui.article.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.plavelo.prs.databinding.FragmentArticleListItemBinding
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.ArticleId

class ArticleListAdapter(
    private val onClick: (ArticleId) -> Unit,
) : ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(ArticleDiffCallback) {

    class ArticleViewHolder(
        private val binding: FragmentArticleListItemBinding,
        private val onClick: (ArticleId) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentArticle: Article

        fun bind(article: Article) {
            currentArticle = article
            val content = article.content
            binding.root.setOnClickListener {
                onClick(currentArticle.id)
            }
            if (content != null) {
                binding.textView.text = HtmlCompat
                    .fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()
                    // remove all Object Replacement Character
                    .replace("\ufffc", "")
            }
            val image = article.image
            if (image != null) {
                binding.imageView.load(image)
            }
        }
    }

    override fun getItemCount() = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = FragmentArticleListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }
}

object ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
