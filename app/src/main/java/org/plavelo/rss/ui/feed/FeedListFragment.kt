package org.plavelo.rss.ui.feed

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.plavelo.rss.R
import org.plavelo.rss.databinding.FragmentFeedListBinding
import org.plavelo.rss.domain.Feed
import org.plavelo.rss.ui.RssViewModel

@AndroidEntryPoint
class FeedListFragment : Fragment() {

    private val viewModel by activityViewModels<RssViewModel>()
    private lateinit var binding: FragmentFeedListBinding
    private lateinit var feedAdapter: FeedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedListBinding.inflate(layoutInflater)
        feedAdapter = FeedListAdapter()
        binding.recyclerView.also {
            it.adapter = feedAdapter
            val dividerItemDecoration = DividerItemDecoration(
                requireContext(),
                LinearLayoutManager(requireContext()).orientation,
            )
            it.addItemDecoration(dividerItemDecoration)
            it.layoutManager = LinearLayoutManager(requireContext())
        }
        binding.textInputEditText.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val url = textView.text?.toString()
                // FIXME: Move this validation to the domain or usecase layer
                if (url != null && Patterns.WEB_URL.matcher(url).matches()) {
                    viewModel.add(
                        Feed(url)
                    )
                    val defaultText = getString(R.string.placeholder_add_feed)
                    textView.text = defaultText
                    binding.textInputEditText.setSelection(defaultText.length)
                }
                true
            } else {
                false
            }
        }
        val selection = binding.textInputEditText.text?.length ?: 0
        binding.textInputEditText.setSelection(selection)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.feeds.observe(viewLifecycleOwner, { feeds ->
            if (feeds != null) {
                feedAdapter.submitList(feeds)
            }
            binding.emptyText.visibility = if (feeds.isNullOrEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })
    }
}
