package org.plavelo.prs.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.plavelo.prs.domain.rss.entity.Feed
import org.plavelo.prs.usecase.repository.FeedRepository
import org.plavelo.prs.infrastructure.mapper.toDto
import org.plavelo.prs.infrastructure.mapper.toModel
import org.plavelo.prs.infrastructure.repository.database.dao.FeedDao
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val feedDao: FeedDao,
) : FeedRepository {
    override suspend fun save(feed: Feed) =
        feedDao.insert(feed.toDto())

    override fun list(): Flow<List<Feed>> =
        feedDao.getAll().map {
            it.toModel()
        }
}