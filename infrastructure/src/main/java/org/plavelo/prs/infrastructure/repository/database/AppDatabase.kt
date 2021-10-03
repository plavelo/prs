package org.plavelo.prs.infrastructure.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.plavelo.prs.infrastructure.repository.database.dao.FeedDao
import org.plavelo.prs.infrastructure.repository.database.dto.FeedDto

@Database(entities = [FeedDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
}
