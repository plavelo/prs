package org.plavelo.prs.infrastructure.repository.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import org.plavelo.prs.domain.Article
import org.plavelo.prs.domain.ArticleId
import org.plavelo.prs.domain.ChannelId

@Entity(
    tableName = "articles",
)
data class ArticleDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val channelId: Int,
    val title: String?,
    val link: String?,
    val description: String?,
    val content: String?,
    val date: Date?,
)

fun Article.toDto(): ArticleDto = ArticleDto(
    id = id.value,
    channelId = id.channelId.value,
    title = title,
    link = link,
    description = description,
    content = content,
    date = date,
)
fun ArticleDto.toModel(): Article = Article(
    id = ArticleId(id, ChannelId(channelId)),
    title = title,
    link = link,
    description = description,
    content = content,
    date = date,
)
fun List<ArticleDto>.toModel(): List<Article> = map { it.toModel() }
