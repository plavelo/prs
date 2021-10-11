package org.plavelo.prs.usecase.repository

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.domain.ChannelId
import org.plavelo.prs.domain.Feed

interface RssRepository {
    suspend fun add(feed: Feed)

    suspend fun fetch(feed: Feed)

    fun feeds(): Flow<List<Feed>>

    fun channels(): Flow<List<Channel>>

    fun articles(channelId: ChannelId): Flow<List<Article>>
}
