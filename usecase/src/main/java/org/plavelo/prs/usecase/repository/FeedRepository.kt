package org.plavelo.prs.usecase.repository

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.Feed

interface FeedRepository {
    suspend fun create(feed: Feed)

    fun list(): Flow<List<Feed>>
}
