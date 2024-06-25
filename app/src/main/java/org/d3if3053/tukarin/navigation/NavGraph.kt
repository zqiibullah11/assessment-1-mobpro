package org.d3if3053.tukarin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3053.tukarin.ui.screen.AboutScreen
import org.d3if3053.tukarin.ui.screen.CatatanScreen
import org.d3if3053.tukarin.ui.screen.DetailCatatanScreen
import org.d3if3053.tukarin.ui.screen.KonversiScreen
import org.d3if3053.tukarin.ui.screen.MainScreen

@Composable
fun SetUpnavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            MainScreen(navController)
        }
        composable("aboutScreen") {
            AboutScreen(navController)
        }
        composable(Screen.Konversi.route) {
            KonversiScreen(navController)
        }
        composable(Screen.Catatan.route) {
            CatatanScreen(navController)
        }
        composable(Screen.FormBaru.route) {
            DetailCatatanScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route, arguments = listOf(
                navArgument(KEY_ID_CATATAN){ type = NavType.IntType}
            )
        ){navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(KEY_ID_CATATAN)
            DetailCatatanScreen(navController, id)
        }
    }
}
