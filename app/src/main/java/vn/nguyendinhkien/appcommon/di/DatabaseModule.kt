package vn.nguyendinhkien.appcommon.di

import androidx.room.Room
import org.koin.dsl.module
import vn.nguyendinhkien.appcommon.data.repository.UserRepositoryImpl
import vn.nguyendinhkien.appcommon.data.source.local.AppDatabase
import vn.nguyendinhkien.appcommon.data.source.local.dao.UserDao
import vn.nguyendinhkien.appcommon.domain.repository.UserRepository

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    single<UserDao> { get<AppDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl(get()) }
}