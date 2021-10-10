package org.plavelo.prs.usecase.repository

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.Channel

interface ChannelRepository {
    suspend fun create(channel: Channel)

    fun list(): Flow<List<Channel>>
}
