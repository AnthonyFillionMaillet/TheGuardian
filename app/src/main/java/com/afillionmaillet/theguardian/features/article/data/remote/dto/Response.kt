package com.afillionmaillet.theguardian.features.article.data.remote.dto

data class Response(
    val currentPage: Int,
    val orderBy: String,
    val pageSize: Int,
    val pages: Int,
    val results: List<Content>,
    val startIndex: Int,
    val status: String,
    val total: Int,
    val userTier: String
)