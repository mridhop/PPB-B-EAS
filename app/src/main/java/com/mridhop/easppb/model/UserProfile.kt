package com.mridhop.easppb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "job") val job: String,
    @ColumnInfo(name = "password") val password: String
)
