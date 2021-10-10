package org.plavelo.prs.infrastructure.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.plavelo.prs.infrastructure.repository.database.dao.ArticleDao
import org.plavelo.prs.infrastructure.repository.database.dao.ChannelDao
import org.plavelo.prs.infrastructure.repository.database.dao.FeedDao
import org.plavelo.prs.infrastructure.repository.database.dto.ArticleDto
import org.plavelo.prs.infrastructure.repository.database.dto.ChannelDto
import org.plavelo.prs.infrastructure.repository.database.dto.FeedDto

@Database(
    entities = [
        FeedDto::class,
        ChannelDto::class,
        ArticleDto::class,
    ],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
    abstract fun channelDao(): ChannelDao
    abstract fun articleDao(): ArticleDao

    companion object {
        fun createDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "main-db",
            ).build()
    }
}
