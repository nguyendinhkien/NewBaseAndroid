package vn.vnext.appcommon.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.vnext.appcommon.core.BaseResponse
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.model.login.UserModel
import vn.vnext.appcommon.domain.model.registration.ParamsRegistration

interface IAuthenticationRepository {
    suspend fun login(paramsLogin: ParamsLogin): Flow<BaseResponse<UserModel>>
    suspend fun register(paramsRegistration: ParamsRegistration): Flow<BaseResponse<UserModel>>
}