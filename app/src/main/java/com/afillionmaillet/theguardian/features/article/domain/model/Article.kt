package com.afillionmaillet.theguardian.features.article.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Article (
    val id: String?,
    val title: String?,
    val date: Date?,
    val thumbnail: String?,
) : Parcelable