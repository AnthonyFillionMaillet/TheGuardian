package com.afillionmaillet.theguardian.features.article.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.afillionmaillet.theguardian.R
import com.afillionmaillet.theguardian.core.common.Constants
import com.afillionmaillet.theguardian.core.extensions.navigate
import com.afillionmaillet.theguardian.features.article.domain.model.Article
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleGrouping
import com.afillionmaillet.theguardian.features.article.presentation.ArticleScreens
import com.afillionmaillet.theguardian.features.article.presentation.list.components.ArticleListItem
import com.afillionmaillet.theguardian.ui.theme.Grey
import java.util.*

@Composable
fun ArticleListScreen(
    navController: NavController,
    viewModel: ArticleListViewModel = hiltViewModel()
) {
    ArticleListScreenContent(
        navController = navController,
        state = viewModel.state.value
    )
}

@Composable
fun ArticleListScreenContent(
    navController: NavController,
    state: ArticleListState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag("toolbar"),
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.testTag("list").fillMaxSize()) {
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                    state.articleMap.forEach { (group, articles) ->
                        item {
                            Text(
                                text = stringResource(id = group.title),
                                style = MaterialTheme.typography.body1,
                                color = Grey,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                        item {
                            Card(
                                modifier = Modifier.testTag("cardItem").padding(all = 16.dp),
                                shape = MaterialTheme.shapes.small,
                                backgroundColor = MaterialTheme.colors.surface,
                            ) {
                                Column {
                                    for (article in articles) {
                                        ArticleListItem(
                                            article = article,
                                            onItemClick = {
                                                navController.navigate(
                                                    route = ArticleScreens.ArticleDetailsScreen.route,
                                                    bundleOf(Constants.PARAM_ARTICLE to article)
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .testTag("error")
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.testTag("loader").align(Alignment.Center)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun ArticleListScreenPreview() {
    ArticleListScreenContent(
        navController = rememberNavController(),
        state = ArticleListState(
            isLoading = false,
            articleMap = mapOf(
                ArticleGrouping.CURRENT_WEEK to listOf(
                    Article(
                        "business/2021/oct/13/brexit-to-blame-for-empty-trucks-and-shelves",
                        "Brexit to blame for empty trucks and shelves",
                        Calendar.getInstance().time,
                        "https://media.guim.co.uk/85ca550f30b9fab944c5c132d1b6f7f8ea6553f0/122_0_3256_1955/500.jpg"
                    )
                )
            ),
            error = ""
        )
    )
}