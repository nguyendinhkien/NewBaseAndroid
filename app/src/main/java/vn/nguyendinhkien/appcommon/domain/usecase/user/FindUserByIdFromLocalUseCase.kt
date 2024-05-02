package vn.nguyendinhkien.appcommon.domain.usecase.user

import vn.nguyendinhkien.appcommon.domain.repository.UserRepository
import javax.inject.Inject

class FindUserByIdFromLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: Int) = userRepository.getUserByIdFromLocal(id)
}