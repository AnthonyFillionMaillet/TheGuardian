package com.afillionmaillet.theguardian.features.article.data.remote.dto

fun articleListDto(
    id: String = "environment/2021/nov/05/bit-like-a-clown-boris-johnson-makes-impression-cop26",
    title: String = "‘Like a clown’: what other countries thought of Boris Johnson at Cop26",
    date: String = "2021-11-05T13:46:39Z",
    thumbnail: String = "https://media.guim.co.uk/62f5543c791747c4ed8d457747aa3883b41f9ce7/0_34_4000_2401/500.jpg"
) = ArticleListDto(
    response = Response(
        currentPage = 1,
        orderBy = "newest",
        pageSize = 1,
        pages = 2598,
        results = listOf(
            Content(
                apiUrl = "https://content.guardianapis.com/environment/2021/nov/05/bit-like-a-clown-boris-johnson-makes-impression-cop26",
                fields = Fields(
                    headline = "‘Like a clown’: what other countries thought of Boris Johnson at Cop26",
                    thumbnail = thumbnail
                ),
                id = id,
                isHosted = false,
                pillarId = "pillar/news",
                pillarName = "News",
                sectionId = "environment",
                sectionName = "Environment",
                type = "article",
                webPublicationDate = date,
                webTitle = title,
                webUrl = "https://www.theguardian.com/environment/2021/nov/05/bit-like-a-clown-boris-johnson-makes-impression-cop26"
            )
        ),
        startIndex=1,
        status="ok",
        total=2598,
        userTier="developer"
    )
)

internal fun articleDetailsDto(
    title: String = "Leicester 1-1 Spartak, Ferencvaros 2-3 Celtic",
    content: String = "<div id=\"block-61845ad58f08acc2f87bbfc2\"",
    thumbnail: String = "https://media.guim.co.uk/14b2eed8aecaf94196f604d0a17ce88d1e14d566/131_71_1581_949/500.jpg"
) = ArticleDetailsDto(
    response = DetailsResponse(
        content = Content(
            apiUrl = "https://content.guardianapis.com/football/live/2021/nov/04/genk-west-ham-leicester-city-spartak-moscow-rangers--brondby-europa-league-clockwatch-live",
            fields = Fields(
                body = content,
                headline = title,
                main = "<figure class=\"element element-image\"",
                thumbnail = thumbnail
            ),
            id = "football/live/2021/nov/04/genk-west-ham-leicester-city-spartak-moscow-rangers--brondby-europa-league-clockwatch-live",
            isHosted = false,
            pillarId = "pillar/sport",
            pillarName = "Sport",
            sectionId = "football",
            sectionName = "Football",
            type = "liveblog",
            webPublicationDate = "2021-11-04T22:14:08Z",
            webTitle = "Leicester 1-1 Spartak, Ferencvaros 2-3 Celtic, Spurs 3-2 Vitesse: Euro clockwatch – as it happened",
            webUrl = "https://www.theguardian.com/football/live/2021/nov/04/genk-west-ham-leicester-city-spartak-moscow-rangers--brondby-europa-league-clockwatch-live"
        ),
        status = "ok",
        total = 1,
        userTier = "developer"
    )
)