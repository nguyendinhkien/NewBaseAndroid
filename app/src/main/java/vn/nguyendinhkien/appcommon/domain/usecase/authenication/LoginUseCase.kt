package vn.nguyendinhkien.appcommon.domain.usecase.authenication

import vn.nguyendinhkien.appcommon.domain.model.login.ParamsLogin
import vn.nguyendinhkien.appcommon.domain.repository.IAuthenticationRepository

class LoginUseCase(
    private val repository: IAuthenticationRepository
) {
    suspend operator fun invoke(paramsLogin: ParamsLogin) = repository.login(paramsLogin)
}