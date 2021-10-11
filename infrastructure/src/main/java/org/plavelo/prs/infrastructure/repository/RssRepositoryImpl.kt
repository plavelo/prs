package org.plavelo.prs.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.domain.ChannelId
import org.plavelo.prs.domain.Feed
import org.plavelo.prs.infrastructure.repository.api.RssApi
import org.plavelo.prs.infrastructure.repository.database.dao.ArticleDao
import org.plavelo.prs.infrastructure.repository.database.dao.ChannelDao
import org.plavelo.prs.infrastructure.repository.database.dao.FeedDao
import org.plavelo.prs.infrastructure.repository.database.dto.toDto
import org.plavelo.prs.infrastructure.repository.database.dto.toModel
import org.plavelo.prs.usecase.repository.RssRepository

class RssRepositoryImpl(
    private val feedDao: FeedDao,
    private val channelDao: ChannelDao,
    private val articleDao: ArticleDao,
    private val rssApi: RssApi,
) : RssRepository {

    override suspend fun add(feed: Feed) {
        feedDao.insert(feed.toDto())
    }

    override suspend fun fetch(feed: Feed) {
        val (channel, articles) = rssApi.fetch(feed)
        channelDao.replace(channel.toDto())
        articleDao.replace(articles.toDto())
    }

    override fun feeds(): Flow<List<Feed>> =
        feedDao.getAll().map {
            it.toModel()
        }

    override fun channels(): Flow<List<Channel>> =
        channelDao.getAll().map {
            it.toModel()
        }

    override fun articles(channelId: ChannelId): Flow<List<Article>> =
        articleDao.getByChannelId(channelId.value).map {
            it.toModel()
        }
}
