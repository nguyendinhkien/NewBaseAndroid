package vn.vnext.appcommon.domain.usecase

import vn.vnext.appcommon.domain.repository.UserRepository
import javax.inject.Inject

class FindAllUserFromLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getAllUserFromLocal()
}