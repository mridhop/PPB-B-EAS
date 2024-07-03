package com.mridhop.easppb.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mridhop.easppb.data.repository.UserRepository
import com.mridhop.easppb.model.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun checkPhoneNumberAndNavigate(phoneNumber: String, navController: NavController) {
        viewModelScope.launch {
            if (userRepository.isPhoneNumberExists(phoneNumber)) {
                navController.navigate("login_password/${phoneNumber}")
            } else {
                navController.navigate("register/${phoneNumber}")
            }
        }
    }

    fun loginUser(
        phoneNumber: String,
        password: String,
        navController: NavController,
        context: Context
    ) {
        viewModelScope.launch {
            if (userRepository.checkUserCredentials(phoneNumber, password)) {
                navController.navigate("home/${phoneNumber}")
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Password salah. Silakan coba lagi.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    suspend fun insertUserProfile(userProfile: UserProfile) {
        userRepository.insert(userProfile)
    }
}