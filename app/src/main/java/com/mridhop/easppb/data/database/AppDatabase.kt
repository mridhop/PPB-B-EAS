package com.mridhop.easppb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mridhop.easppb.model.UserProfile

@Database(entities = [UserProfile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}