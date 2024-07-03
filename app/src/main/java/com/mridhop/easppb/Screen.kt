package com.mridhop.easppb

sealed class Screen {
    object WelcomeScreen {
        const val route = "welcome"
    }
    object RegisterScreen {
        const val route = "register"
        fun withArgs(phoneNumber: String): String = "register/$phoneNumber"
    }
    object LoginPasswordScreen {
        const val route = "login_password"
        fun withArgs(phoneNumber: String): String = "login_password/$phoneNumber"
    }
    object RegisterPasswordScreen {
        const val route = "register_password"
        fun withArgs(userProfileJson: String): String = "register_password/$userProfileJson"
    }
    object HomeScreen {
        const val route = "home"
    }
}