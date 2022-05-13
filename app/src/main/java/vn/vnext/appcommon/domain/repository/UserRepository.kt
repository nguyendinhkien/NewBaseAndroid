package vn.vnext.appcommon.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.vnext.appcommon.data.source.local.entities.UserEntity

interface UserRepository {

    suspend fun insertUser(user: UserEntity)

    suspend fun getAllUserFromLocal(): Flow<List<UserEntity>>

    suspend fun getUserByIdFromLocal(id: Int): Flow<UserEntity?>

    suspend fun deleteUser(id: Int)
}