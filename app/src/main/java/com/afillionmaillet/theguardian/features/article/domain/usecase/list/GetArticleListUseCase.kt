package com.afillionmaillet.theguardian.features.article.domain.usecase.list

import com.afillionmaillet.theguardian.core.common.Resource
import com.afillionmaillet.theguardian.features.article.data.remote.dto.toArticleList
import com.afillionmaillet.theguardian.features.article.domain.model.Article
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleDetails
import com.afillionmaillet.theguardian.features.article.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading<List<Article>>())
            val articleList = repository.getArticleList(query).toArticleList()
            emit(Resource.Success<List<Article>>(articleList))
        } catch(e: HttpException) {
            if (e.code() == 403) {
                emit(Resource.Error<List<Article>>("Forbidden access for this API key"))
            } else {
                emit(Resource.Error<List<Article>>(e.localizedMessage ?: "An unexpected error occured"))
            }
        } catch(e: IOException) {
            emit(Resource.Error<List<Article>>("Couldn't reach server. Check your internet connection."))
        }
    }
}