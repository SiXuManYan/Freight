package com.ftacloud.freightuser.storage.dao

import androidx.room.*
import com.ftacloud.freightuser.storage.entity.User

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