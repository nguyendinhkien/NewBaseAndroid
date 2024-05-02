package vn.nguyendinhkien.appcommon.domain.usecase.authenication

import vn.nguyendinhkien.appcommon.domain.model.login.ParamsLogin
import vn.nguyendinhkien.appcommon.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IAuthenticationRepository
) {
    suspend operator fun invoke(paramsLogin: ParamsLogin) = repository.login(paramsLogin)
}