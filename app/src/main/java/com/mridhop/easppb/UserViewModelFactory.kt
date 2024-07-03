package com.mridhop.easppb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mridhop.easppb.data.repository.UserRepository
import com.mridhop.easppb.ui.viewmodel.UserViewModel

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}