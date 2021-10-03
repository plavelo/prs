package org.plavelo.prs.infrastructure.repository.database.dto

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "feeds",
    indices = [Index(
        value = ["url"],
        unique = true
    )]
)
data class FeedDto(
    val url: String,
)