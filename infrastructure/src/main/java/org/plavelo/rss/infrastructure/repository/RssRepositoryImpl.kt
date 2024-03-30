package org.plavelo.rss.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.plavelo.rss.domain.Article
import org.plavelo.rss.domain.ArticleId
import org.plavelo.rss.domain.Channel
import org.plavelo.rss.domain.ChannelId
import org.plavelo.rss.domain.Feed
import org.plavelo.rss.infrastructure.repository.api.RssApi
import org.plavelo.rss.infrastructure.repository.database.dao.ArticleDao
import org.plavelo.rss.infrastructure.repository.database.dao.ChannelDao
import org.plavelo.rss.infrastructure.repository.database.dao.FeedDao
import org.plavelo.rss.infrastructure.repository.database.dto.toDto
import org.plavelo.rss.infrastructure.repository.database.dto.toModel
import org.plavelo.rss.usecase.repository.RssRepository

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
        val channelId = channelDao.replace(channel.toDto())
        articleDao.replace(
            articles.toDto().map {
                it.copy(channelId = channelId)
            }
        )
    }

    override fun feeds(): Flow<List<Feed>> =
        feedDao.getAll().map {
            it.toModel()
        }

    override fun channels(): Flow<List<Channel>> =
        channelDao.getAll().map {
            it.toModel()
        }

    override fun article(articleId: ArticleId): Flow<Article> =
        articleDao.getByArticleId(articleId.value).map {
            it.toModel()
        }

    override fun articles(channelId: ChannelId): Flow<List<Article>> =
        articleDao.getByChannelId(channelId.value).map {
            it.toModel()
        }
}
