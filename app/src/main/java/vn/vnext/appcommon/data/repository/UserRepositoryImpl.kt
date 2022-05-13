package vn.vnext.appcommon.data.repository

import kotlinx.coroutines.flow.Flow
import vn.vnext.appcommon.data.source.local.dao.UserDao
import vn.vnext.appcommon.data.source.local.entities.UserEntity
import vn.vnext.appcommon.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
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