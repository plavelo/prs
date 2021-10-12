package org.plavelo.prs.ui.article.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import org.plavelo.prs.databinding.FragmentArticlePagerBinding
import org.plavelo.prs.ui.RssViewModel

class ArticlePagerFragment : Fragment() {

    private val viewModel by activityViewModels<RssViewModel>()
    private lateinit var binding: FragmentArticlePagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlePagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.channels.observe(viewLifecycleOwner) { channels ->
            if (channels != null) {
                binding.pager.adapter = ArticlePagerAdapter(this, channels)

                TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
                    tab.text = channels[position].title
                }.attach()
            }
        }
    }
}
