package com.afillionmaillet.theguardian.features.article.domain.usecase.list

import com.afillionmaillet.theguardian.core.common.Resource
import com.afillionmaillet.theguardian.di.FakeArticleRepository
import com.afillionmaillet.theguardian.features.article.domain.model.article
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetArticleListUseCaseTest {

    lateinit var useCase: GetArticleListUseCase
    private lateinit var fakeArticleRepository: FakeArticleRepository

    @Before
    fun setUp() {
        fakeArticleRepository = FakeArticleRepository()
        useCase = GetArticleListUseCase(fakeArticleRepository)
    }

    @Test
    fun `Should get article list correctly`() = runBlocking {
        useCase("football and brexit").collect { result ->
            when (result) {
                is Resource.Loading -> {
                    assertEquals(null, result.data)
                    assertEquals(null, result.message)
                }
                is Resource.Success -> {
                    val expected = listOf(article())
                    assertEquals(expected, result.data)
                    assertEquals(null, result.message)
                }
                else -> {}
            }
        }
    }
}