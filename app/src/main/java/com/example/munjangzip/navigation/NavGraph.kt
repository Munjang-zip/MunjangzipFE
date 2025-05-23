package com.example.munjanzipr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.munjangzip.feature.auth.StartScreen
import com.example.munjangzip.feature.user.UserInfoScreen
import com.example.munjangzip.feature.user.UserInfoScreen2


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController = navController)
        }

        composable("userInfo") {
            UserInfoScreen(navController = navController)
        }

        composable("userInfo2") {
            UserInfoScreen2(navController = navController)
        }


    }
}
