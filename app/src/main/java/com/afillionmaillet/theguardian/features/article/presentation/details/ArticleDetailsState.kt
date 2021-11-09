package com.afillionmaillet.theguardian.features.article.presentation.details

import com.afillionmaillet.theguardian.features.article.domain.model.ArticleDetails

data class ArticleDetailsState(
    val isLoading: Boolean = false,
    val article: ArticleDetails? = null,
    var error: String = ""
)