package org.plavelo.prs.infrastructure.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.infrastructure.repository.database.dto.ArticleDto

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<ArticleDto>>

    @Insert
    suspend fun insert(article: ArticleDto)

    @Delete
    suspend fun delete(article: ArticleDto)
}
