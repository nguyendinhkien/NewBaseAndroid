package vn.vnext.appcommon.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.vnext.appcommon.data.source.local.dao.UserDao
import vn.vnext.appcommon.data.source.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "MyDatabase.db"
    }
}