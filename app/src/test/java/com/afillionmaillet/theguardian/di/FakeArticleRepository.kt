package com.afillionmaillet.theguardian.di

import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleDetailsDto
import com.afillionmaillet.theguardian.features.article.data.remote.dto.ArticleListDto
import com.afillionmaillet.theguardian.features.article.domain.repository.ArticleRepository
import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.InputStream

class FakeArticleRepository : ArticleRepository {

    private val moshi: Moshi = Moshi.Builder().build()
    private lateinit var reader: BufferedReader
    private var content: String = ""

    override suspend fun getArticleList(query: String): ArticleListDto {
        val inputStream = loadLocalJson("fakeArticleListDto.json")
        reader = BufferedReader(inputStream?.reader())
        reader.use { content = it.readText() }
        return moshi.adapter(ArticleListDto::class.java).fromJson(content)!!
    }

    override suspend fun getArticleDetails(articleId: String): ArticleDetailsDto {
        val inputStream = loadLocalJson("fakeArticleDetailsDto.json")
        reader = BufferedReader(inputStream?.reader())
        reader.use { content = it.readText() }
        return moshi.adapter(ArticleDetailsDto::class.java).fromJson(content)!!
    }

    private fun loadLocalJson(fileName: String): InputStream? {
        return javaClass.classLoader?.getResourceAsStream("api/$fileName")
    }
}