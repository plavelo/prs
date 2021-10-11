package org.plavelo.prs.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.plavelo.prs.databinding.FragmentArticleListBinding
import org.plavelo.prs.domain.ChannelId
import org.plavelo.prs.ui.RssViewModel

class ArticleListFragment : Fragment() {

    private val viewModel by activityViewModels<RssViewModel>()
    private lateinit var articleAdapter: ArticleListAdapter
    private lateinit var binding: FragmentArticleListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(layoutInflater)
        articleAdapter = ArticleListAdapter()
        binding.recyclerView.also {
            it.adapter = articleAdapter
            val dividerItemDecoration = DividerItemDecoration(
                requireContext(),
                LinearLayoutManager(requireContext()).orientation,
            )
            it.addItemDecoration(dividerItemDecoration)
            it.layoutManager = LinearLayoutManager(requireContext())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_CHANNEL_ID) }?.apply {
            val channelId = ChannelId(getInt(ARG_CHANNEL_ID))
            viewModel.articles(channelId).observe(viewLifecycleOwner, {
                it?.let {
                    articleAdapter.submitList(it)
                }
            })
        }
    }

    companion object {
        const val ARG_CHANNEL_ID = "channelId"
    }
}
