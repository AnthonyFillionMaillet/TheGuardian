package com.afillionmaillet.theguardian.features.article.presentation.list

import com.afillionmaillet.theguardian.features.article.domain.model.Article
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleGrouping

data class ArticleListState(
    val isLoading: Boolean = false,
    val articleMap: Map<ArticleGrouping, List<Article>> = emptyMap(),
    var error: String = ""
)