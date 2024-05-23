package vn.nguyendinhkien.appcommon.domain.usecase.authenication

import vn.nguyendinhkien.appcommon.domain.model.registration.ParamsRegistration
import vn.nguyendinhkien.appcommon.domain.repository.IAuthenticationRepository

class RegistrationUseCase(
    private val repository: IAuthenticationRepository
) {
    suspend operator fun invoke(paramsRegistration: ParamsRegistration) =
        repository.register(paramsRegistration)
}