package vn.nguyendinhkien.appcommon.domain.usecase.user

import vn.nguyendinhkien.appcommon.domain.repository.UserRepository
class FindAllUserFromLocalUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getAllUserFromLocal()
}