package com.ftacloud.freightuser.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


/**
 * 应用内数据库
 * 创建新表时 实体类 + 实体类Dao接口 后在此添加
 */
@Database(
    entities = [
        User::class

    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CloudDataBase : RoomDatabase() {

    companion object {
        private const val DatabaseFileName = "CloudAccount.db"
        private var instance: CloudDataBase? = null

        @Synchronized
        fun get(context: Context): CloudDataBase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, CloudDataBase::class.java, DatabaseFileName)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }


    }

    abstract fun userDao(): UserDao


}