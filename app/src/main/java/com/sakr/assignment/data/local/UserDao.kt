package com.sakr.assignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UsersDao {
    //for single user insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    //checking if user exist or not in our db
    @Query("SELECT * FROM User_Table WHERE email LIKE :email AND password LIKE :password")
    suspend fun readLoginData(email: String, password: String): User


    //getting user data details
    @Query("select * from User_Table where id Like :id")
    fun getUserDataDetails(id: Long): User

    //deleting all user from db
    @Query("DELETE FROM User_Table")
    fun deleteAll()


}