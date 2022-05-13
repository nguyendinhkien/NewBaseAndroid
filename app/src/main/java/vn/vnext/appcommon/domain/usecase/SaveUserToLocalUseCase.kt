package vn.vnext.appcommon.domain.usecase

import vn.vnext.appcommon.data.source.local.entities.UserEntity
import vn.vnext.appcommon.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserToLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: UserEntity) = userRepository.insertUser(user)
}