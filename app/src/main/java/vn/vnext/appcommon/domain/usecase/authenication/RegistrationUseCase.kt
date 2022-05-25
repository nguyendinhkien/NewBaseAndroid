package vn.vnext.appcommon.domain.usecase.authenication

import vn.vnext.appcommon.domain.model.registration.ParamsRegistration
import vn.vnext.appcommon.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: IAuthenticationRepository
) {
    suspend operator fun invoke(paramsRegistration: ParamsRegistration) =
        repository.register(paramsRegistration)
}