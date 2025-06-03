package com.example.munjanzipr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.munjangzip.UserInfo.Pets.PetName
import com.example.munjangzip.UserInfo.Pets.PetSelect
import com.example.munjangzip.feature.auth.StartScreen
import com.example.munjangzip.feature.user.UserInfoScreen
import com.example.munjangzip.feature.user.UserInfoScreen2
import com.example.munjangzip.page.MainCategoryPage

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController = navController)
        }

        composable("userInfo") {
            UserInfoScreen(navController = navController)
        }

        composable(
            route = "userInfo2?nickname={nickname}&libraryName={libraryName}",
            arguments = listOf(
                navArgument("nickname") { type = NavType.StringType; nullable = true },
                navArgument("libraryName") { type = NavType.StringType; nullable = true }
            )
        ) { backStackEntry ->
            val nickname = backStackEntry.arguments?.getString("nickname")
            val libraryName = backStackEntry.arguments?.getString("libraryName")
            UserInfoScreen2(navController = navController, nickname = nickname, libraryName = libraryName)
        }

        composable("petSelect?nickname={nickname}&libraryName={libraryName}",
            arguments = listOf(
                navArgument("nickname") { nullable = true },
                navArgument("libraryName") { nullable = true }
            )
        ) { backStackEntry ->
            val nickname = backStackEntry.arguments?.getString("nickname")
            val libraryName = backStackEntry.arguments?.getString("libraryName")
            PetSelect(navController, nickname, libraryName)
        }

        composable(
            route = "PetName/{pet}?nickname={nickname}&libraryName={libraryName}",
            arguments = listOf(
                navArgument("pet") { type = NavType.StringType; nullable = true },
                navArgument("nickname") { type = NavType.StringType; nullable = true },
                navArgument("libraryName") { type = NavType.StringType; nullable = true }
            )
        ) { backStackEntry ->
            PetName(navController = navController, backStackEntry = backStackEntry)
        }

        composable("mainCategoryPage") {
            MainCategoryPage(navController = navController)
        }
    }
}