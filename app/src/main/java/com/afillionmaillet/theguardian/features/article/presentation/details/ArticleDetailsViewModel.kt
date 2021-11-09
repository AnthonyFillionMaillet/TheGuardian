package com.afillionmaillet.theguardian.features.article.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afillionmaillet.theguardian.core.common.Constants
import com.afillionmaillet.theguardian.core.common.Resource
import com.afillionmaillet.theguardian.features.article.domain.model.Article
import com.afillionmaillet.theguardian.features.article.domain.usecase.details.GetArticleDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val getArticleDetailsUseCase: GetArticleDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ArticleDetailsState())
    val state: State<ArticleDetailsState> = _state

    init {
        savedStateHandle.get<Article>(Constants.PARAM_ARTICLE)?.let { article ->
            getArticleDetails(article.id ?: "")
        }
    }

    private fun getArticleDetails(articleId: String) {
        getArticleDetailsUseCase(articleId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ArticleDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ArticleDetailsState(article = result.data)
                }
                is Resource.Error -> {
                    _state.value = ArticleDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}