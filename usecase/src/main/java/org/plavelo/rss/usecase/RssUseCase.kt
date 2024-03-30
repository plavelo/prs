package org.plavelo.rss.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.plavelo.rss.domain.Article
import org.plavelo.rss.domain.ArticleId
import org.plavelo.rss.domain.Channel
import org.plavelo.rss.domain.ChannelId
import org.plavelo.rss.domain.Feed
import org.plavelo.rss.usecase.repository.RssRepository

class RssUseCase @Inject constructor(
    private val rssRepository: RssRepository,
) {
    fun feeds(): Flow<List<Feed>> =
        rssRepository.feeds()

    fun channels(): Flow<List<Channel>> =
        rssRepository.channels()

    fun article(articleId: ArticleId): Flow<Article> =
        rssRepository.article(articleId)

    fun articles(channelId: ChannelId): Flow<List<Article>> =
        rssRepository.articles(channelId)

    suspend fun add(feed: Feed) {
        rssRepository.fetch(feed)
        // If the fetch succeeds, add the feed
        rssRepository.add(feed)
    }

    suspend fun reload() {
        val feeds = rssRepository.feeds().first()
        for (feed in feeds) {
            rssRepository.fetch(feed)
        }
    }
}
