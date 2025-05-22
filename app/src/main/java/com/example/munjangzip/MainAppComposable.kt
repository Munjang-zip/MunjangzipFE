package com.example.munjangzip

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.munjanzipr.ui.theme.MunJanZipRTheme
import com.example.munjanzipr.navigation.NavGraph

@Composable
fun MainAppComposable() {
    val navController = rememberNavController()

    MunJanZipRTheme {
        NavGraph(navController = navController)
    }
}
