package org.plavelo.prs.ui.article.list

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.plavelo.prs.domain.Channel

class ArticlePagerAdapter(
    fragment: Fragment,
    private val channels: List<Channel>,
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = channels.size

    override fun createFragment(position: Int): Fragment =
        ArticleListFragment.newInstance(channels[position].id)
}
