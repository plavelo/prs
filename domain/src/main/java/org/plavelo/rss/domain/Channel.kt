package org.plavelo.rss.domain

data class Channel(
    val id: ChannelId,
    val title: String?,
    val link: String?,
    val description: String?,
)
