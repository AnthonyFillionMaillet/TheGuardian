package com.afillionmaillet.theguardian.features.article.presentation.list

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.afillionmaillet.theguardian.R
import com.afillionmaillet.theguardian.features.article.domain.model.Article
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleGrouping
import com.afillionmaillet.theguardian.features.article.presentation.ArticleActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@HiltAndroidTest
class ArticleListScreenTest {

    private lateinit var navController: TestNavHostController
    private lateinit var viewModel: ArticleListViewModel

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<ArticleActivity>()

//    @Inject
//    lateinit var useCase: GetArticleListUseCase

    private val articleMapData = mapOf(
        ArticleGrouping.CURRENT_WEEK to listOf(
            Article(
                "business/2021/oct/13/brexit-to-blame-for-empty-trucks-and-shelves",
                "Brexit to blame for empty trucks and shelves",
                Calendar.getInstance().time,
                "https://media.guim.co.uk/85ca550f30b9fab944c5c132d1b6f7f8ea6553f0/122_0_3256_1955/500.jpg"
            )
        )
    )

    @Before
    fun setUp() {
        hiltTestRule.inject()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
//        viewModel = ArticleListViewModel(useCase)
//        composeTestRule.setContent {
//            ArticleListScreenContent(navController = navController, state = ArticleListState())
//        }
//        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")
    }

    @Test
    fun `Should show toolbar and display app name`() {
        // Given
        val context = composeTestRule.activity
        val state = ArticleListState()

        // When
        composeTestRule.setContent {
            ArticleListScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNode(hasTestTag("toolbar"))
            .assertIsDisplayed()
            .onChild()
            .assertTextContains(context.getString(R.string.app_name))
    }

    @Test
    fun `Should display loader when retrieving data from server`() {
        // Given
        val state = ArticleListState(isLoading = true)

        // When
        composeTestRule.setContent {
            ArticleListScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("loader").assertExists()
        composeTestRule.onNodeWithTag("error").assertDoesNotExist()
    }

    @Test
    fun `Should display error message when server sends error`() {
        // Given
        val state = ArticleListState(error = "An error occured")

        // When
        composeTestRule.setContent {
            ArticleListScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("loader").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error").assertExists()
    }

    @Test
    fun `Should not display any articles when not retrieved from server`() {
        // Given
        val state = ArticleListState(articleMap = emptyMap())

        // When
        composeTestRule.setContent {
            ArticleListScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("list").onChildren().assertCountEquals(0)
    }

    @Test
    fun `Should display all articles retrieved from server`() {
        // Given
        val articleMap = articleMapData
        val state = ArticleListState(articleMap = articleMap)

        // When
        composeTestRule.setContent {
            ArticleListScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("loader").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error").assertDoesNotExist()
        val groupCount = articleMap.keys.size
        val articleCount = articleMap.values.size
        composeTestRule
            .onNodeWithTag("list")
            .onChildren()
            .assertCountEquals(groupCount + articleCount)
        articleMap.forEach { (_, articles) ->
            articles.forEach { article ->
                article.title?.let {
                    composeTestRule.onNodeWithText(it).assertExists()
                }
            }
        }
    }
}