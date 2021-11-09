package com.afillionmaillet.theguardian.core.data.remote

import com.afillionmaillet.theguardian.core.common.Constants
import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleDetailsDto
import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheGuardianApi {

    @GET("search")
    suspend fun getArticleList(
        @Query("q") query: String,
        @Query("show-fields") fields: String = "headline,thumbnail",
        @Query("page-size") pageSize: String = "50",
        @Query("order-by") orderBy: String = "newest",
        @Query("api-key") apiKey: String = Constants.API_KEY,
    ): ArticleListDto

    @GET("{articleId}")
    suspend fun getArticleDetails(
        @Path("articleId", encoded = true) articleId: String,
        @Query("show-fields") fields: String = "main,body,headline,thumbnail",
        @Query("api-key") apiKey: String = Constants.API_KEY,
    ): ArticleDetailsDto
}