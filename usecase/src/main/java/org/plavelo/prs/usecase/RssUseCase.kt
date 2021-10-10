package org.plavelo.prs.usecase

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.Feed
import org.plavelo.prs.usecase.repository.FeedRepository
import javax.inject.Inject
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.domain.ChannelId
import org.plavelo.prs.usecase.repository.ArticleRepository
import org.plavelo.prs.usecase.repository.ChannelRepository

class RssUseCase @Inject constructor(
    private val feedRepository: FeedRepository,
    private val channelRepository: ChannelRepository,
    private val articleRepository: ArticleRepository,
) {
    fun feeds(): Flow<List<Feed>> =
        feedRepository.list()

    fun channels(): Flow<List<Channel>> =
        channelRepository.list()

    fun articles(channeiId: ChannelId): Flow<List<Article>> =
        articleRepository.list()

    suspend fun add(feed: Feed) =
        feedRepository.create(feed)
}
