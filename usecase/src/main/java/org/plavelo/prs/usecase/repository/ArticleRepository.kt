package org.plavelo.prs.usecase.repository

import kotlinx.coroutines.flow.Flow
import org.plavelo.prs.domain.Article

interface ArticleRepository {
    suspend fun create(article: Article)

    fun list(): Flow<List<Article>>
}
