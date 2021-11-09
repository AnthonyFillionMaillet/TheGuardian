package com.afillionmaillet.theguardian.features.article.presentation.details

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.afillionmaillet.theguardian.features.article.domain.model.ArticleDetails
import com.afillionmaillet.theguardian.features.article.presentation.ArticleActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ArticleDetailsScreenTest {

    private lateinit var navController: TestNavHostController

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<ArticleActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    private val articleDetailsData = ArticleDetails(
        "Brexit to blame for empty trucks and shelves",
        "<p>There is much talk that the fuel shortages and empty supermarket shelves we are seeing " +
                "(<a href=\\\"https://www.theguardian.com/business/2021/oct/04/uk-fuel-crisis-could-go-on-for" +
                "-further-week-despite-military-help\\\">",
        "https://media.guim.co.uk/85ca550f30b9fab944c5c132d1b6f7f8ea6553f0/122_0_3256_1955/500.jpg"
    )

    @Test
    fun `Should show empty toolbar with clickable back button`() {
        // Given
        val state = ArticleDetailsState()

        // When
        composeTestRule.setContent {
            ArticleDetailsScreenContent(navController = navController, state = state)
        }

        // Then
        val toolbar = composeTestRule.onNodeWithTag("toolbar").assertIsDisplayed()
        toolbar.onChildren()
            .filterToOne(hasTestTag("toolbarText"))
            .assertTextContains("")
        toolbar.onChildren()
            .filterToOne(hasContentDescription("toolbarIcon"))
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun `Should display loader when retrieving data from server`() {
        // Given
        val state = ArticleDetailsState(isLoading = true)

        // When
        composeTestRule.setContent {
            ArticleDetailsScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("loader").assertExists()
        composeTestRule.onNodeWithTag("error").assertDoesNotExist()
    }

    @Test
    fun `Should display error message when server sends error`() {
        // Given
        val state = ArticleDetailsState(error = "An error occured")

        // When
        composeTestRule.setContent {
            ArticleDetailsScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("loader").assertDoesNotExist()
        composeTestRule.onNodeWithTag("error").assertExists()
    }

    @Test
    fun `Should not display article details when not retrieved from server`() {
        // Given
        val state = ArticleDetailsState(article = ArticleDetails(null, null, null))

        // When
        composeTestRule.setContent {
            ArticleDetailsScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("image").assertDoesNotExist()
        composeTestRule.onNodeWithTag("title").assertDoesNotExist()
        composeTestRule.onNodeWithTag("content").assertDoesNotExist()
    }

    @Test
    fun `Should display article details when retrieved from server`() {
        // Given
        val state = ArticleDetailsState(article = articleDetailsData)

        // When
        composeTestRule.setContent {
            ArticleDetailsScreenContent(navController = navController, state = state)
        }

        // Then
        composeTestRule.onNodeWithTag("image").assertExists()
        composeTestRule.onNodeWithTag("title").assertExists()
        composeTestRule.onNodeWithTag("content").assertExists()
    }
}