package com.afillionmaillet.theguardian.features.article.data.remote

import com.afillionmaillet.theguardian.features.article.data.remote.dto.articleDetailsDto
import com.afillionmaillet.theguardian.features.article.data.remote.dto.toArticleDetails
import com.afillionmaillet.theguardian.features.article.domain.model.articleDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleDetailsDtoTest {

    @Test
    fun `Map article details dto into article details correctly`() {
        val title = "Leicester 1-1 Spartak, Ferencvaros 2-3 Celtic"
        val content = "<div id=\"block-61845ad58f08acc2f87bbfc2\""
        val thumbnail = "https://media.guim.co.uk/14b2eed8aecaf94196f604d0a17ce88d1e14d566/131_71_1581_949/500.jpg"
        val dto = articleDetailsDto(
            title = title,
            content = content,
            thumbnail = thumbnail
        )

        val actual = dto.toArticleDetails()

        val expected = articleDetails(title = title, content = content, thumbnail = thumbnail)

        assertEquals(expected, actual)
    }
}