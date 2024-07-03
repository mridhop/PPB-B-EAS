package com.mridhop.easppb.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mridhop.easppb.DatabaseBuilder
import com.mridhop.easppb.UserViewModelFactory
import com.mridhop.easppb.data.repository.UserRepository
import com.mridhop.easppb.ui.viewmodel.UserViewModel

object ViewModelUtil {
    @Composable
    fun getUserViewModel(): UserViewModel {
        val context = LocalContext.current
        return getUserViewModel(context)
    }

    @Composable
    fun getUserViewModel(context: Context): UserViewModel {
        val database = DatabaseBuilder.getDatabase(context)
        val userRepository = UserRepository(database.userProfileDao())
        // Assuming you have a way to access ViewModelStoreOwner, e.g., an Activity or Fragment
        // This line might need to be adjusted based on your architecture
        return viewModel(factory = UserViewModelFactory(userRepository))
    }
}