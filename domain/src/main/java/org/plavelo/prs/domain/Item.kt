package org.plavelo.prs.domain

import java.util.Date

data class Item(
    val title: String,
    val link: String,
    val description: String,
    val date: Date,
    val subjects: List<String>,
    val content: String,
)
