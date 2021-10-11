package org.plavelo.prs.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.plavelo.prs.R
import org.plavelo.prs.ui.RssViewModel

class ArticlePagerFragment : Fragment() {
    private val viewModel by activityViewModels<RssViewModel>()
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        viewPager = view.findViewById(R.id.pager)
        viewModel.channels.observe(viewLifecycleOwner) { channels ->
            if (channels != null) {
                viewPager.adapter = ArticlePagerAdapter(this, channels)

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = channels[position].title
                }.attach()
            }
        }
    }
}
