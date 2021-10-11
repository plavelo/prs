package org.plavelo.prs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.plavelo.prs.domain.Feed
import org.plavelo.prs.usecase.RssUseCase
import javax.inject.Inject
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.domain.ChannelId

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

    fun articles(channelId: ChannelId): LiveData<List<Article>> =
        rssUseCase
            .articles(channelId)
            .asLiveData()

    fun add(feed: Feed) = viewModelScope.launch {
        rssUseCase.add(feed)
    }
}
