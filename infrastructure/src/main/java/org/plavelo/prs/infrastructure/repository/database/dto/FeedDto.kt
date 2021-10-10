package org.plavelo.prs.infrastructure.repository.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "feeds",
)
data class FeedDto(
    @PrimaryKey
    val url: String,
)