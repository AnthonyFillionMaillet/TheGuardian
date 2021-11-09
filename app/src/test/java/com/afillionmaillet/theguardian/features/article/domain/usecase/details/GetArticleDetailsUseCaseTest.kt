package com.afillionmaillet.theguardian.features.article.domain.usecase.details

import com.afillionmaillet.theguardian.core.common.Resource
import com.afillionmaillet.theguardian.di.FakeArticleRepository
import com.afillionmaillet.theguardian.features.article.domain.model.articleDetails
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetArticleDetailsUseCaseTest {

    lateinit var useCase: GetArticleDetailsUseCase
    private lateinit var fakeArticleRepository: FakeArticleRepository

    @Before
    fun setUp() {
        fakeArticleRepository = FakeArticleRepository()
        useCase = GetArticleDetailsUseCase(fakeArticleRepository)
    }

    @Test
    fun `Should get article details correctly`() = runBlocking {
        useCase("environment/2021/nov/05/bit-like-a-clown-boris-johnson-makes-impression-cop26").collect { result ->
            when (result) {
                is Resource.Loading -> {
                    assertEquals(null, result.data)
                    assertEquals(null, result.message)
                }
                is Resource.Success -> {
                    val expected = articleDetails()
                    assertEquals(expected, result.data)
                    assertEquals(null, result.message)
                }
                else -> {}
            }
        }
    }
}