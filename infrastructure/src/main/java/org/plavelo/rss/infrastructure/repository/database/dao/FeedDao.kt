package org.plavelo.rss.infrastructure.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.plavelo.rss.infrastructure.repository.database.dto.FeedDto

@Dao
interface FeedDao {
    @Query("SELECT * FROM feeds")
    fun getAll(): Flow<List<FeedDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(feed: FeedDto)

    @Delete
    suspend fun delete(feed: FeedDto)
}
