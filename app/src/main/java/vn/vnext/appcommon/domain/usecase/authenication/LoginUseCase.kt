package vn.vnext.appcommon.domain.usecase.authenication

import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IAuthenticationRepository
) {
    suspend operator fun invoke(paramsLogin: ParamsLogin) = repository.login(paramsLogin)
}