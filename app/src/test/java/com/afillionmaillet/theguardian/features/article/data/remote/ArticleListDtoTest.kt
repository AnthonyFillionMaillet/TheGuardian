package com.afillionmaillet.theguardian.features.article.data.remote

import com.afillionmaillet.theguardian.features.article.data.remote.dto.articleListDto
import com.afillionmaillet.theguardian.features.article.data.remote.dto.toArticleList
import com.afillionmaillet.theguardian.features.article.domain.model.article
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ArticleListDtoTest {

    @Test
    fun `Map article list dto into article correctly`() {
        val id = "environment/2021/nov/05/bit-like-a-clown-boris-johnson-makes-impression-cop26"
        val title = "‘Like a clown’: what other countries thought of Boris Johnson at Cop26"
        val date = "2021-11-05T13:46:39Z"
        val dateParsed = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(date) ?: Calendar.getInstance().time
        val thumbnail = "https://media.guim.co.uk/62f5543c791747c4ed8d457747aa3883b41f9ce7/0_34_4000_2401/500.jpg"
        val dto = articleListDto(
            id = id,
            title = title,
            date = date,
            thumbnail = thumbnail
        )

        val actual = dto.toArticleList()

        val expected = listOf(article(id = id, title = title, date = dateParsed, thumbnail = thumbnail))

        assertEquals(expected, actual)
    }
}