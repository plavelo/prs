package org.plavelo.prs.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.domain.ChannelId
import org.plavelo.prs.domain.Feed
import org.plavelo.prs.usecase.repository.RssRepository

class RssUseCase @Inject constructor(
    private val rssRepository: RssRepository,
) {
    fun feeds(): Flow<List<Feed>> =
        rssRepository.feeds()

    fun channels(): Flow<List<Channel>> =
        rssRepository.channels()

    fun articles(channelId: ChannelId): Flow<List<Article>> =
        rssRepository.articles(channelId)

    suspend fun add(feed: Feed) {
        rssRepository.add(feed)
        rssRepository.fetch(feed)
    }

    suspend fun reload() {
        val feeds = rssRepository.feeds().first()
        for (feed in feeds) {
            rssRepository.fetch(feed)
        }
    }
}
