package org.plavelo.prs.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.plavelo.prs.infrastructure.repository.RssRepositoryImpl
import org.plavelo.prs.infrastructure.repository.api.RssApi
import org.plavelo.prs.infrastructure.repository.database.AppDatabase
import org.plavelo.prs.usecase.repository.RssRepository

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
