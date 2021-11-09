package com.afillionmaillet.theguardian.features.article.presentation.details

import android.text.SpannableString
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat.fromHtml
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleDetails
import com.afillionmaillet.theguardian.features.article.presentation.details.components.HtmlText

@Composable
fun ArticleDetailsScreen(
    navController: NavController,
    viewModel: ArticleDetailsViewModel = hiltViewModel()
) {
    ArticleDetailsScreenContent(
        navController = navController,
        state = viewModel.state.value
    )
}

@Composable
fun ArticleDetailsScreenContent(
    navController: NavController,
    state: ArticleDetailsState
) {
    Scaffold(
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                state.article.let {
                    LazyColumn(modifier = Modifier.testTag("list").fillMaxSize()) {
                        item {
                            Box {
                                it?.thumbnail?.let {
                                    Image(
                                        painter = rememberImagePainter(
                                            data = it,
                                        ),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .testTag("image")
                                            .fillMaxWidth()
                                            .height(250.dp)
                                    )
                                }
                                TopAppBar(
                                    modifier = Modifier.testTag("toolbar"),
                                    title = { Text(text = "", modifier = Modifier.testTag("toolbarText")) },
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Transparent,
                                    elevation = 0.dp,
                                    navigationIcon = {
                                        IconButton(onClick = { navController.popBackStack() }) {
                                            Icon(
                                                Icons.Filled.ArrowBack,
                                                contentDescription = "toolbarIcon",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                )
                            }
                            it?.title?.let {
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.h1,
                                    modifier = Modifier
                                        .testTag("title")
                                        .padding(horizontal = 16.dp)
                                )
                            }
                            it?.content?.let {
                                Spacer(modifier = Modifier.height(16.dp))
                                HtmlText(
                                    text = SpannableString(
                                        fromHtml(it, FROM_HTML_MODE_LEGACY)
                                    ),
                                    modifier = Modifier
                                        .testTag("content")
                                        .padding(horizontal = 16.dp)
                                )
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
fun ArticleDetailsScreenPreview() {
    ArticleDetailsScreenContent(
        navController = rememberNavController(),
        state = ArticleDetailsState(
            isLoading = false,
            article = ArticleDetails(
                "Brexit to blame for empty trucks and shelves",
                "<p>There is much talk that the fuel shortages and empty supermarket shelves we are seeing (<a href=\\\"https://www.theguardian.com/business/2021/oct/04/uk-fuel-crisis-could-go-on-for-further-week-despite-military-help\\\">UK fuel crisis ‘could go on for further week’ despite military help, 4 October</a>) have been caused by the lack of lorry drivers, and that this is a problem that predated Brexit. On the face of it, this could have been the case. However, the UK’s exit from the EU removed the advantages of cabotage. This is an efficient system that has green benefits as it eases the movement of goods. It allows previously empty journeys to be replaced by paying loads that fill gaps in the logistics network, preventing such vehicle and driver shortages.</p> <p>Being part of the EU’s cabotage system meant that European- or UK-registered lorries could transport goods from a truck’s home country to the destination country, be it Manchester or Milan. Cabotage allowed any EU-registered vehicle to pick up a return load from Manchester and take it to London, or Paris to&nbsp;Brussels. From Brussels, another&nbsp;load could be collected and delivered to Berlin or Warsaw&nbsp;or Cambridge.</p> <p>But leaving the EU has limited the ability of European trucks operating in the UK to take up our shortfall. It has also removed the incentive for EU trucks to travel to the UK. As a result, many European trucks travel empty to Dover or another exit port. It is not just the shortage of drivers that is a problem, but also the absence of vehicles. So the shortfalls are a direct result of the UK leaving the EU and the Tory government’s incompetent&nbsp;negotiations.<br><strong>Peter Morris </strong><br><em>King’s Lynn, Norfolk</em></p> <p><strong>• <em>Have an opinion on anything you’ve read in the Guardian today? Please </em></strong><a href=\\\"mailto:guardian.letters@theguardian.com\\\"><strong><em>email</em></strong></a><strong><em> us your letter and it will be considered for publication.</em></strong></p>",
                "https://media.guim.co.uk/85ca550f30b9fab944c5c132d1b6f7f8ea6553f0/122_0_3256_1955/500.jpg"
            ),
            error = ""
        )
    )
}