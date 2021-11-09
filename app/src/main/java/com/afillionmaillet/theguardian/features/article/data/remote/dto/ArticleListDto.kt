package com.afillionmaillet.theguardian.features.article.data.remote.dto

import com.afillionmaillet.theguardian.features.article.domain.model.Article
import java.text.SimpleDateFormat
import java.util.*

data class ArticleListDto(
    val response: Response
)

fun ArticleListDto.toArticleList(): List<Article> {
    return response.results.map {
        Article(
            id = it.id,
            title = it.fields.headline,
            date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(it.webPublicationDate),
            thumbnail = it.fields.thumbnail
        )
    }
}

