package org.plavelo.rss.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import kotlinx.coroutines.launch
import org.plavelo.rss.domain.Feed
import org.plavelo.rss.usecase.RssUseCase
import javax.inject.Inject
import kotlinx.coroutines.SupervisorJob
import org.plavelo.rss.domain.Article
import org.plavelo.rss.domain.ArticleId
import org.plavelo.rss.domain.Channel
import org.plavelo.rss.domain.ChannelId

@HiltViewModel
class RssViewModel @Inject constructor(
    private val rssUseCase: RssUseCase,
) : ViewModel() {

    val feeds: LiveData<List<Feed>> =
        rssUseCase
            .feeds()
            .asLiveData()

    val channels: LiveData<List<Channel>> =
        rssUseCase
            .channels()
            .asLiveData()

    fun article(articleId: ArticleId): LiveData<Article> =
        rssUseCase
            .article(articleId)
            .asLiveData()

    fun articles(channelId: ChannelId): LiveData<List<Article>> =
        rssUseCase
            .articles(channelId)
            .asLiveData()

    fun add(feed: Feed) = viewModelScope.launch(SupervisorJob()) {
        try {
            rssUseCase.add(feed)
        } catch (e: Exception) {
            // FIXME: do nothing yet
        }
    }

    fun reload(onFinished: () -> Unit) = viewModelScope.launch {
        rssUseCase.reload()
        onFinished()
    }
}
