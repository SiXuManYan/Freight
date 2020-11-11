package com.fatcloud.account.storage.dao

import androidx.room.*
import com.fatcloud.account.storage.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("SELECT * FROM tb_user")
    fun findUser(): User?

    @Query("DELETE FROM tb_user")
    fun clear()


    @Update
    fun updateUser(user: User)
}