package org.plavelo.prs.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.plavelo.prs.databinding.FragmentFeedListBinding
import org.plavelo.prs.domain.Feed
import org.plavelo.prs.ui.RssViewModel

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
        binding.textInputEditText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                val url = textView.text?.toString()
                if (url != null) {
                    viewModel.add(
                        Feed(url)
                    )
                    textView.clearComposingText()
                }
                true
            } else {
                false
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.feeds.observe(viewLifecycleOwner, {
            it?.let {
                feedAdapter.submitList(it)
            }
        })
    }
}
