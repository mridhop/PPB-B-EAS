package com.mridhop.easppb

import android.content.Context
import androidx.room.Room
import com.mridhop.easppb.data.database.AppDatabase

object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}