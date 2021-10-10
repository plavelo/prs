package org.plavelo.prs.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.plavelo.prs.domain.feed.Feed
import org.plavelo.prs.infrastructure.mapper.toDto
import org.plavelo.prs.infrastructure.mapper.toModel
import org.plavelo.prs.infrastructure.repository.database.dao.FeedDao
import org.plavelo.prs.usecase.repository.FeedRepository

class FeedRepositoryImpl(
    private val feedDao: FeedDao,
) : FeedRepository {
    override suspend fun create(feed: Feed) =
        feedDao.insert(feed.toDto())

    override fun list(): Flow<List<Feed>> =
        feedDao.getAll().map {
            it.toModel()
        }
}