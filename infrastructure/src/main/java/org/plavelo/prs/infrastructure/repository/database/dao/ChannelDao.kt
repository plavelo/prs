package org.plavelo.prs.infrastructure.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.infrastructure.repository.database.dto.ChannelDto

@Dao
interface ChannelDao {
    @Query("SELECT * FROM channels")
    fun getAll(): Flow<List<ChannelDto>>

    @Insert
    suspend fun insert(channel: ChannelDto)

    @Delete
    suspend fun delete(channel: ChannelDto)
}
