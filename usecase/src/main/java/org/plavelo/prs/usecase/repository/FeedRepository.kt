package org.plavelo.prs.usecase.repository

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.feed.Feed

interface FeedRepository {
    suspend fun create(feed: Feed)

    fun list(): Flow<List<Feed>>
}
