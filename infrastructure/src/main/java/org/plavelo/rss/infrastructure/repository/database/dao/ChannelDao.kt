package org.plavelo.rss.infrastructure.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.plavelo.rss.infrastructure.repository.database.dto.ChannelDto

@Dao
interface ChannelDao {
    @Query("SELECT * FROM channels")
    fun getAll(): Flow<List<ChannelDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(channel: ChannelDto): Long

    @Delete
    suspend fun delete(channel: ChannelDto)
}
