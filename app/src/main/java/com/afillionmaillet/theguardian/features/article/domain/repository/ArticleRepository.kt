package com.afillionmaillet.theguardian.features.article.domain.repository

import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleDetailsDto
import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleListDto

interface ArticleRepository {

    suspend fun getArticleList(query: String): ArticleListDto

    suspend fun getArticleDetails(articleId: String): ArticleDetailsDto
}