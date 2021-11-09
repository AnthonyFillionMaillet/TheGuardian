package com.afillionmaillet.theguardian.features.article.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.afillionmaillet.theguardian.core.extensions.toObliqueStrokeFormat
import com.afillionmaillet.theguardian.features.article.domain.model.Article
import com.afillionmaillet.theguardian.ui.theme.Grey
import java.util.*

@Preview(showBackground = true)
@Composable
fun ArticleListItem(
    @PreviewParameter(ArticlePreview::class) article: Article,
    onItemClick: (Article) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(article) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = article.thumbnail,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .size(64.dp)
        )
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            article.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            article.date?.let {
                Text(
                    text = it.toObliqueStrokeFormat(),
                    style = MaterialTheme.typography.body2,
                    color = Grey
                )
            }
        }
    }
}

class ArticlePreview : PreviewParameterProvider<Article> {
    override val values = sequenceOf(
        Article(
            "business/2021/oct/13/brexit-to-blame-for-empty-trucks-and-shelves",
            "Brexit to blame for empty trucks and shelves",
            Calendar.getInstance().time,
            "https://media.guim.co.uk/85ca550f30b9fab944c5c132d1b6f7f8ea6553f0/122_0_3256_1955/500.jpg"
        )
    )
}