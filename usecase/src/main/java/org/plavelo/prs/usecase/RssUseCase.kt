package org.plavelo.prs.usecase

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.Feed
import org.plavelo.prs.usecase.repository.RssRepository
import javax.inject.Inject
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.domain.ChannelId

class RssUseCase @Inject constructor(
    private val rssRepository: RssRepository,
) {
    fun feeds(): Flow<List<Feed>> =
        rssRepository.feeds()

    fun channels(): Flow<List<Channel>> =
        rssRepository.channels()

    fun articles(channelId: ChannelId): Flow<List<Article>> =
        rssRepository.articles(channelId)

    suspend fun add(feed: Feed) =
        rssRepository.add(feed)
}
