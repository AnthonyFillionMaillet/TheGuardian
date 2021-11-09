package com.afillionmaillet.theguardian.features.article.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afillionmaillet.theguardian.features.article.presentation.details.ArticleDetailsScreen
import com.afillionmaillet.theguardian.features.article.presentation.list.ArticleListScreen
import com.afillionmaillet.theguardian.ui.theme.TheGuardianAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheGuardianAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ArticleScreens.ArticleListScreen.route
                    ) {
                        composable(
                            route = ArticleScreens.ArticleListScreen.route
                        ) {
                            ArticleListScreen(navController)
                        }
                        composable(
                            route = ArticleScreens.ArticleDetailsScreen.route,
                        ) {
                            ArticleDetailsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}