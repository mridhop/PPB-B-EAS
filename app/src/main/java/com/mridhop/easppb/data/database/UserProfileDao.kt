package com.mridhop.easppb.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mridhop.easppb.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile")
    fun getAll(): Flow<List<UserProfile>>

    @Query("SELECT * FROM user_profile WHERE phone_number = :phoneNumber")
    fun getByPhoneNumber(phoneNumber: String): Flow<UserProfile>

    @Query("SELECT * FROM user_profile WHERE phone_number = :phoneNumber AND password = :password")
    fun getByPhoneNumberAndPassword(phoneNumber: String, password: String): Flow<UserProfile>

    @Query("SELECT EXISTS(SELECT * FROM user_profile WHERE phone_number = :phoneNumber)")
    suspend fun isPhoneNumberExists(phoneNumber: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user_profile WHERE phone_number = :phoneNumber AND password = :password)")
    suspend fun checkUserCredentials(phoneNumber: String, password: String): Boolean

    @Insert
    suspend fun insert(userProfile: UserProfile)

    @Update
    suspend fun update(userProfile: UserProfile)

    @Delete
    suspend fun delete(userProfile: UserProfile)
}