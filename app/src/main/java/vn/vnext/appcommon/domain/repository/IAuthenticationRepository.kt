package vn.vnext.appcommon.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.vnext.appcommon.core.BaseResponse
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.model.login.UserModel

interface IAuthenticationRepository {
    suspend fun login(paramsLogin: ParamsLogin): Flow<BaseResponse<UserModel>>
}