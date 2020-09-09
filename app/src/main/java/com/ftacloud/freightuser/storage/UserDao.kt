package com.ftacloud.freightuser.storage

import androidx.room.*

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