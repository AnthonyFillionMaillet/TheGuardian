package com.afillionmaillet.theguardian.features.article.domain.model

import java.text.SimpleDateFormat
import java.util.*

internal fun article(
    id: String = "environment/2021/nov/05/bit-like-a-clown-boris-johnson-makes-impression-cop26",
    title: String  = "‘Like a clown’: what other countries thought of Boris Johnson at Cop26",
    date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse("2021-11-05T13:46:39Z") ?: Calendar.getInstance().time,
    thumbnail: String  = "https://media.guim.co.uk/62f5543c791747c4ed8d457747aa3883b41f9ce7/0_34_4000_2401/500.jpg"
) = Article(
    id,
    title,
    date,
    thumbnail
)

internal fun articleDetails(
    title: String = "Leicester 1-1 Spartak, Ferencvaros 2-3 Celtic",
    content: String = "<div id=\"block-61845ad58f08acc2f87bbfc2\"",
    thumbnail: String = "https://media.guim.co.uk/14b2eed8aecaf94196f604d0a17ce88d1e14d566/131_71_1581_949/500.jpg"
) = ArticleDetails(
    title,
    content,
    thumbnail
)