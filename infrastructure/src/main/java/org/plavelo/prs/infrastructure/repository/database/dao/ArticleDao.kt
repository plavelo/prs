package org.plavelo.prs.infrastructure.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.infrastructure.repository.database.dto.ArticleDto

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles WHERE id = :articleId LIMIT 1")
    fun getByArticleId(articleId: Long): Flow<ArticleDto>

    @Query("SELECT * FROM articles WHERE channelId = :channelId")
    fun getByChannelId(channelId: Long): Flow<List<ArticleDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(article: ArticleDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(articles: List<ArticleDto>)

    @Delete
    suspend fun delete(article: ArticleDto)
}
