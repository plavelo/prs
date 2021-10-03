package org.plavelo.prs.usecase

import org.plavelo.prs.domain.rss.entity.Feed
import org.plavelo.prs.usecase.repository.FeedRepository
import javax.inject.Inject

class FeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository,
) {
    suspend fun add(feed: Feed) =
        feedRepository.create(feed)
}
