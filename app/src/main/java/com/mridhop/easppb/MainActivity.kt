package com.mridhop.easppb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mridhop.easppb.ui.theme.EASPPBTheme
import com.mridhop.easppb.ui.view.HomeScreen
import com.mridhop.easppb.ui.view.LoginPasswordScreen
import com.mridhop.easppb.ui.view.RegisterPasswordScreen
import com.mridhop.easppb.ui.view.RegisterScreen
import com.mridhop.easppb.ui.view.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EASPPBTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route) {
        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable("${Screen.RegisterScreen.route}/{phoneNumber}") { backStackEntry ->
            RegisterScreen(
                navController = navController,
                phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            )
        }
        composable("${Screen.LoginPasswordScreen.route}/{phoneNumber}") { backStackEntry ->
            LoginPasswordScreen(
                navController = navController,
                phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            )
        }
        composable("${Screen.RegisterPasswordScreen.route}/{userProfileJson}") {
            backStackEntry ->
            RegisterPasswordScreen(
                navController = navController,
                userProfileJson = backStackEntry.arguments?.getString("userProfileJson") ?: ""
            )
        }
        composable("${Screen.HomeScreen.route}/{phoneNumber}") {
            backStackEntry ->
            HomeScreen(
                navController = navController,
                phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            )
        }
    }
}