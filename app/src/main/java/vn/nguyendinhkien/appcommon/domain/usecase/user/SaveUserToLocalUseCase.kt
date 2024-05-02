package vn.nguyendinhkien.appcommon.domain.usecase.user

import vn.nguyendinhkien.appcommon.data.source.local.entities.UserEntity
import vn.nguyendinhkien.appcommon.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserToLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: UserEntity) = userRepository.insertUser(user)
}