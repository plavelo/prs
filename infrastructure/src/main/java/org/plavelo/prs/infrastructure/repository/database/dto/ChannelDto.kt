package org.plavelo.prs.infrastructure.repository.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.plavelo.prs.domain.Channel
import org.plavelo.prs.domain.ChannelId

@Entity(
    tableName = "channels",
)
data class ChannelDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
