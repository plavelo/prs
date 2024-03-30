package org.plavelo.rss.infrastructure.repository.database.dto

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.plavelo.rss.domain.Channel
import org.plavelo.rss.domain.ChannelId

@Entity(
    tableName = "channels",
    indices = [Index(
        value = ["link"],
        unique = true
    )]
)
data class ChannelDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String?,
    val link: String?,
    val description: String?,
)

fun Channel.toDto(): ChannelDto = ChannelDto(
    id = 0,
    title = title,
    link = link,
    description = description,
)

fun ChannelDto.toModel(): Channel = Channel(
    id = ChannelId(id),
    title = title,
    link = link,
    description = description,
)

fun List<ChannelDto>.toModel(): List<Channel> = map { it.toModel() }
