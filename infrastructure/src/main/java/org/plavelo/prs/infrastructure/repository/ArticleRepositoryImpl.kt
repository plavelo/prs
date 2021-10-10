package org.plavelo.prs.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.plavelo.prs.domain.Article
import org.plavelo.prs.infrastructure.repository.database.dao.ArticleDao
import org.plavelo.prs.infrastructure.repository.database.dto.toDto
import org.plavelo.prs.infrastructure.repository.database.dto.toModel
import org.plavelo.prs.usecase.repository.ArticleRepository

class ArticleRepositoryImpl(
    private val articleDao: ArticleDao,
) : ArticleRepository {
    override suspend fun create(article: Article) =
        articleDao.insert(article.toDto())

    override fun list(): Flow<List<Article>> =
        articleDao.getAll().map {
            it.toModel()
        }
}
