package com.afillionmaillet.theguardian.features.article.data.repository

import com.afillionmaillet.theguardian.core.data.remote.TheGuardianApi
import com.afillionmaillet.theguardian.core.extensions.enqueueResponse
import com.afillionmaillet.theguardian.features.article.data.remote.dto.articleDetailsDto
import com.afillionmaillet.theguardian.features.article.data.remote.dto.articleListDto
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ArticleRepositoryImplTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val theGuardianApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TheGuardianApi::class.java)

    private val api = ArticleRepositoryImpl(theGuardianApi)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Should fetch article list with 200 response`() {
        mockWebServer.enqueueResponse("fakeArticleListDto.json", 200)

        runBlocking {
            val actual = api.getArticleList("football and brexit")
            val expected = articleListDto()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `Should fetch article details with 200 response`() {
        mockWebServer.enqueueResponse("fakeArticleDetailsDto.json", 200)

        runBlocking {
            val actual = api.getArticleDetails("environment/2021/nov/05/bit-like-a-clown-boris-johnson-makes-impression-cop26")
            val expected = articleDetailsDto()

            assertEquals(expected, actual)
        }
    }
}