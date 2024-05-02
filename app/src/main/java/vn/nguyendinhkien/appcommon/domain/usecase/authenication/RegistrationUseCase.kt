package vn.nguyendinhkien.appcommon.domain.usecase.authenication

import vn.nguyendinhkien.appcommon.domain.model.registration.ParamsRegistration
import vn.nguyendinhkien.appcommon.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: IAuthenticationRepository
) {
    suspend operator fun invoke(paramsRegistration: ParamsRegistration) =
        repository.register(paramsRegistration)
}