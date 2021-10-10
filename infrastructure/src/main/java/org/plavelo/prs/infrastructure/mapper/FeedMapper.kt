package org.plavelo.prs.infrastructure.mapper

import org.plavelo.prs.domain.feed.Feed
import org.plavelo.prs.infrastructure.repository.database.dto.FeedDto

fun Feed.toDto(): FeedDto = FeedDto(url = url)

fun FeedDto.toModel(): Feed = Feed(url)
fun List<FeedDto>.toModel(): List<Feed> = map { it.toModel() }
