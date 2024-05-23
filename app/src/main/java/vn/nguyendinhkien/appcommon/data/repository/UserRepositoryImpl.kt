package vn.nguyendinhkien.appcommon.data.repository

import kotlinx.coroutines.flow.Flow
import vn.nguyendinhkien.appcommon.data.source.local.dao.UserDao
import vn.nguyendinhkien.appcommon.data.source.local.entities.UserEntity
import vn.nguyendinhkien.appcommon.domain.repository.UserRepository

class UserRepositoryImpl (
    private val userDao: UserDao
) : UserRepository {

    override suspend fun insertUser(user: UserEntity) {
        return userDao.insertUser(user)
    }

    override suspend fun getAllUserFromLocal(): Flow<List<UserEntity>> {
        return userDao.getAllUser()
    }

    override suspend fun getUserByIdFromLocal(id: Int): Flow<UserEntity?> {
        return userDao.getUserById(id)
    }

    override suspend fun deleteUser(id: Int) {
        return userDao.deleteUser(id)
    }
}