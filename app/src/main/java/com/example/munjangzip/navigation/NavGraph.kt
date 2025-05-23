package com.example.munjanzipr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.munjangzip.UserInfo.Pets.PetName
import com.example.munjangzip.UserInfo.Pets.PetSelect
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

        composable("petSelect") {
            PetSelect( navController = navController)
        }
        // 전달된 petName 받아서 PetName.kt로 넘김
        composable("PetName/{petName}") { backStackEntry ->
            val petName = backStackEntry.arguments?.getString("petName")
            PetName(petName = petName)
        }


    }


}

