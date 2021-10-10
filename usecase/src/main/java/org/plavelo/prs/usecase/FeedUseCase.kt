package org.plavelo.prs.usecase

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.feed.Feed
import org.plavelo.prs.usecase.repository.FeedRepository
import javax.inject.Inject

class FeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository,
) {
    fun list(): Flow<List<Feed>> =
        feedRepository.list()

    suspend fun add(feed: Feed) =
        feedRepository.create(feed)
}
