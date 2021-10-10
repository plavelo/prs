package org.plavelo.prs.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.infrastructure.repository.database.dao.ChannelDao
import org.plavelo.prs.infrastructure.repository.database.dto.toDto
import org.plavelo.prs.infrastructure.repository.database.dto.toModel
import org.plavelo.prs.usecase.repository.ChannelRepository

class ChannelRepositoryImpl(
    private val channelDao: ChannelDao,
) : ChannelRepository {
    override suspend fun create(channel: Channel) =
        channelDao.insert(channel.toDto())

    override fun list(): Flow<List<Channel>> =
        channelDao.getAll().map {
            it.toModel()
        }
}
