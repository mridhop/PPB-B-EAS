package com.mridhop.easppb.data.repository

import com.mridhop.easppb.data.database.UserProfileDao
import com.mridhop.easppb.model.UserProfile

class UserRepository(private val userProfileDao: UserProfileDao) {
    suspend fun isPhoneNumberExists(phoneNumber: String): Boolean {
        return userProfileDao.isPhoneNumberExists(phoneNumber)
    }

    suspend fun insert(userProfile: UserProfile) {
        userProfileDao.insert(userProfile)
    }
}