package org.plavelo.prs.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.plavelo.prs.infrastructure.repository.ArticleRepositoryImpl
import org.plavelo.prs.infrastructure.repository.ChannelRepositoryImpl
import org.plavelo.prs.infrastructure.repository.FeedRepositoryImpl
import org.plavelo.prs.infrastructure.repository.database.AppDatabase
import org.plavelo.prs.usecase.repository.ArticleRepository
import org.plavelo.prs.usecase.repository.ChannelRepository
import org.plavelo.prs.usecase.repository.FeedRepository

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.createDatabase(context)

    @Provides
    fun provideFeedRepository(appDatabase: AppDatabase): FeedRepository =
        FeedRepositoryImpl(appDatabase.feedDao())

    @Provides
    fun provideChannelRepository(appDatabase: AppDatabase): ChannelRepository =
        ChannelRepositoryImpl(appDatabase.channelDao())

    @Provides
    fun provideArticleRepository(appDatabase: AppDatabase): ArticleRepository =
        ArticleRepositoryImpl(appDatabase.articleDao())
}
