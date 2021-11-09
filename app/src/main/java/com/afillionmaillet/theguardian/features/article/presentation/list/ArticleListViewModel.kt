package com.afillionmaillet.theguardian.features.article.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afillionmaillet.theguardian.core.common.Resource
import com.afillionmaillet.theguardian.core.extensions.isDateInCurrentWeek
import com.afillionmaillet.theguardian.core.extensions.isDateInLastWeek
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleGrouping
import com.afillionmaillet.theguardian.features.article.domain.usecase.list.GetArticleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val getArticleListUseCase: GetArticleListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ArticleListState())
    val state: State<ArticleListState> = _state

    init {
        getArticleList("football and brexit")
    }

    private fun getArticleList(query: String) {
        getArticleListUseCase(query).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ArticleListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ArticleListState(articleMap = result.data?.groupBy {
                        when {
                            it.date?.isDateInCurrentWeek() == true -> ArticleGrouping.CURRENT_WEEK
                            it.date?.isDateInLastWeek() == true -> ArticleGrouping.LAST_WEEK
                            else -> ArticleGrouping.OLDER
                        }
                    } ?: emptyMap())
                }
                is Resource.Error -> {
                    _state.value = ArticleListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}