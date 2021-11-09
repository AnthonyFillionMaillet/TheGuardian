package com.afillionmaillet.theguardian.features.article.data.repository

import com.afillionmaillet.theguardian.core.data.remote.TheGuardianApi
import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleDetailsDto
import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleListDto
import com.afillionmaillet.theguardian.features.article.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: TheGuardianApi
) : ArticleRepository {

    override suspend fun getArticleList(query: String): ArticleListDto {
        return api.getArticleList(query = query)
    }

    override suspend fun getArticleDetails(articleId: String): ArticleDetailsDto {
        return api.getArticleDetails(articleId = articleId)
    }
}