package org.plavelo.rss.usecase.repository

import kotlinx.coroutines.flow.Flow
import org.plavelo.rss.domain.Article
import org.plavelo.rss.domain.ArticleId
import org.plavelo.rss.domain.Channel
import org.plavelo.rss.domain.ChannelId
import org.plavelo.rss.domain.Feed

interface RssRepository {
    suspend fun add(feed: Feed)

    suspend fun fetch(feed: Feed)

    fun feeds(): Flow<List<Feed>>

    fun channels(): Flow<List<Channel>>

    fun article(articleId: ArticleId): Flow<Article>

    fun articles(channelId: ChannelId): Flow<List<Article>>
}
