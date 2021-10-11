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
    @Query("SELECT * FROM articles WHERE channelId = :channelId")
    fun getByChannelId(channelId: Int): Flow<List<ArticleDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(article: ArticleDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(articles: List<ArticleDto>)

    @Delete
    suspend fun delete(article: ArticleDto)
}
