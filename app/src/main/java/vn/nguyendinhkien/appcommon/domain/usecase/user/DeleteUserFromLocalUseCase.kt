package vn.nguyendinhkien.appcommon.domain.usecase.user

import vn.nguyendinhkien.appcommon.domain.repository.UserRepository

class DeleteUserFromLocalUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: Int) = userRepository.deleteUser(id)
}