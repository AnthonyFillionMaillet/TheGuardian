package com.afillionmaillet.theguardian.features.article.domain.model

import androidx.annotation.StringRes
import com.afillionmaillet.theguardian.R

enum class ArticleGrouping(@StringRes var title: Int) {
    CURRENT_WEEK(R.string.current_week),
    LAST_WEEK(R.string.last_week),
    OLDER(R.string.older_articles)
}