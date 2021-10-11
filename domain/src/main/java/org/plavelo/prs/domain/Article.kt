package org.plavelo.prs.domain

import java.util.Date

data class Article(
    val id: ArticleId,
    val title: String?,
    val link: String?,
    val description: String?,
    val image: String?,
    val content: String?,
    val date: Date?,
)
