package com.afillionmaillet.theguardian.features.article.data.remote.dto

import com.afillionmaillet.theguardian.features.article.domain.model.ArticleDetails

data class ArticleDetailsDto(
    val response: DetailsResponse
)

fun ArticleDetailsDto.toArticleDetails(): ArticleDetails {
    return response.content.let {
        ArticleDetails(
            title = it.fields.headline,
            content = it.fields.body,
            thumbnail = it.fields.thumbnail
        )
    }
}