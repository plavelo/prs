package org.plavelo.prs.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticlePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = ArticleListFragment()
        fragment.arguments = Bundle().apply {
            putInt(ArticleListFragment.ARG_OBJECT, position + 1)
        }
        return fragment
    }
}
