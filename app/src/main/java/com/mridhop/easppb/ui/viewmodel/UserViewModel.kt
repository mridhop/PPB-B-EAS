package com.mridhop.easppb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mridhop.easppb.data.repository.UserRepository
import com.mridhop.easppb.model.UserProfile
import kotlinx.coroutines.launch

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

    fun insertUserProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            userRepository.insert(userProfile)
        }
    }
}