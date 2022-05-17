package vn.vnext.appcommon.domain.usecase.user

import vn.vnext.appcommon.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserFromLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: Int) = userRepository.deleteUser(id)
}