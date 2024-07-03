package com.mridhop.easppb

sealed class Screen {
    object WelcomeScreen {
        const val route = "welcome"
    }
    object RegisterScreen {
        const val route = "register"
    }
    object LoginPasswordScreen {
        const val route = "login_password"
    }
    object RegisterPasswordScreen {
        const val route = "register_password"
    }
    object HomeScreen {
        const val route = "home"
    }
}