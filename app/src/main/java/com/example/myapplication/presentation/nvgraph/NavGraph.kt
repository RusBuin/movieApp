package com.example.myapplication.presentation.nvgraph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.onboarding.OnBoardingViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.home.HomeViewModel

import com.example.myapplication.presentation.onboarding.OnBoardingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    startDestination: String
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(route = Route.OnBoardingScreen.route){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.Navigation.route,
            startDestination = Route.NavigationScreen.route
        ){
            composable(route = Route.NavigationScreen.route){
                Scaffold(topBar = {
                    TopAppBar(
                        title = {Text(text = "Navigator Screen") }
                    )
                }) { padding->

                    val viewModel: HomeViewModel = hiltViewModel()
                    val result = viewModel.movies.collectAsLazyPagingItems()
                    HomeScreen(movie = result, navigate = {})
                     }

            }
        }
    }
}