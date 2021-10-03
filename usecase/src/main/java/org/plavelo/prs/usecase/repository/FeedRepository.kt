package org.plavelo.prs.usecase.repository

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.rss.entity.Feed

interface FeedRepository {
    suspend fun save(feed: Feed)

    fun list(): Flow<List<Feed>>
}
