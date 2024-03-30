package org.plavelo.rss.ui.article.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.plavelo.rss.databinding.FragmentArticleListBinding
import org.plavelo.rss.domain.ChannelId
import org.plavelo.rss.ui.RssViewModel

class ArticleListFragment : Fragment() {

    private val viewModel by activityViewModels<RssViewModel>()
    private lateinit var articleAdapter: ArticleListAdapter
    private lateinit var binding: FragmentArticleListBinding
    private lateinit var channelId: ChannelId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            channelId = ChannelId(
                it.getLong(ARG_CHANNEL_ID)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(layoutInflater)
        articleAdapter = ArticleListAdapter { articleId ->
            val action = ArticlePagerFragmentDirections
                .actionArticleListFragmentToArticleDetailFragment(
                    articleId = articleId.value,
                    channelId = articleId.channelId.value,
                )
            findNavController().navigate(action)
        }
        binding.recyclerView.also {
            it.adapter = articleAdapter
            val dividerItemDecoration = DividerItemDecoration(
                requireContext(),
                LinearLayoutManager(requireContext()).orientation,
            )
            it.addItemDecoration(dividerItemDecoration)
            it.layoutManager = LinearLayoutManager(requireContext())
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.reload {
                binding.swipeRefresh.isRefreshing = false
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.articles(channelId).observe(viewLifecycleOwner, {
            it?.let {
                articleAdapter.submitList(it)
            }
        })
    }

    companion object {
        private const val ARG_CHANNEL_ID = "channelId"

        fun newInstance(channelId: ChannelId): ArticleListFragment =
            ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_CHANNEL_ID, channelId.value)
                }
            }
    }
}
