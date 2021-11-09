package com.afillionmaillet.theguardian.features.article.data.remote.dto

data class DetailsResponse(
    val content: Content,
    val status: String,
    val total: Int,
    val userTier: String
)