package org.plavelo.prs.infrastructure.repository.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.plavelo.prs.domain.Feed

@Entity(
    tableName = "feeds",
)
data class FeedDto(
    @PrimaryKey
    val url: String,
)

fun Feed.toDto(): FeedDto = FeedDto(url = url)
fun FeedDto.toModel(): Feed = Feed(url)
fun List<FeedDto>.toModel(): List<Feed> = map { it.toModel() }
