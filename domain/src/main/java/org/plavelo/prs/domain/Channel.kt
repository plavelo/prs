package org.plavelo.prs.domain

import java.net.URL

data class Channel(
    val title: String,
    val link: URL,
    val description: String,
    val items: List<String>,
)
