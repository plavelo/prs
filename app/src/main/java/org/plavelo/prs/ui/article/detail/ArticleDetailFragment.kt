package org.plavelo.prs.ui.article.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import org.plavelo.prs.R
import org.plavelo.prs.databinding.FragmentArticleDetailBinding
import org.plavelo.prs.domain.ArticleId
import org.plavelo.prs.domain.ChannelId
import org.plavelo.prs.ui.RssViewModel

class ArticleDetailFragment : Fragment() {

    private val viewModel by activityViewModels<RssViewModel>()
    private val args: ArticleDetailFragmentArgs by navArgs()
    private lateinit var articleId: ArticleId
    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleId = ArticleId(
            args.articleId,
            ChannelId(
                args.channelId,
            ),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailBinding.inflate(layoutInflater)
        binding.webView.run {
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.defaultFontSize = resources.getDimensionPixelSize(R.dimen.article_font_size)
        }
        binding.toolbar.run {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.article(articleId).observe(viewLifecycleOwner, { article ->
            val content = article.content
            if (content != null) {
                binding
                    .webView
                    .loadData(content, "text/html", null)
            }
        })
    }
}
