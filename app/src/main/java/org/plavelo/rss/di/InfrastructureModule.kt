package org.plavelo.rss.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.plavelo.rss.infrastructure.repository.RssRepositoryImpl
import org.plavelo.rss.infrastructure.repository.api.RssApi
import org.plavelo.rss.infrastructure.repository.database.AppDatabase
import org.plavelo.rss.usecase.repository.RssRepository

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.createDatabase(context)

    @Provides
    fun provideFeedRepository(
        @ApplicationContext context: Context,
        appDatabase: AppDatabase,
    ): RssRepository =
        RssRepositoryImpl(
            appDatabase.feedDao(),
            appDatabase.channelDao(),
            appDatabase.articleDao(),
            RssApi(context),
        )
}
