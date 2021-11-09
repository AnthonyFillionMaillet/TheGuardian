package com.afillionmaillet.theguardian.features.article.presentation

sealed class ArticleScreens(val route: String) {
    object ArticleListScreen: ArticleScreens("article_list_screen")
    object ArticleDetailsScreen: ArticleScreens("article_details_screen")
}
