package com.afillionmaillet.theguardian.features.article.domain.usecase.details

import com.afillionmaillet.theguardian.core.common.Resource
import com.afillionmaillet.theguardian.features.article.data.remote.dto.toArticleDetails
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleDetails
import com.afillionmaillet.theguardian.features.article.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArticleDetailsUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(articleId: String): Flow<Resource<ArticleDetails>> = flow {
        try {
            emit(Resource.Loading<ArticleDetails>())
            val article = repository.getArticleDetails(articleId).toArticleDetails()
            emit(Resource.Success<ArticleDetails>(article))
        } catch(e: HttpException) {
            emit(Resource.Error<ArticleDetails>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ArticleDetails>("Couldn't reach server. Check your internet connection."))
        }
    }
}