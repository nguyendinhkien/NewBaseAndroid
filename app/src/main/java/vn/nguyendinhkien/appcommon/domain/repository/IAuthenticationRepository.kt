package vn.nguyendinhkien.appcommon.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.nguyendinhkien.appcommon.core.BaseResponse
import vn.nguyendinhkien.appcommon.domain.model.login.ParamsLogin
import vn.nguyendinhkien.appcommon.domain.model.login.UserModel
import vn.nguyendinhkien.appcommon.domain.model.registration.ParamsRegistration

interface IAuthenticationRepository {
    suspend fun login(paramsLogin: ParamsLogin): Flow<BaseResponse<UserModel>>
    suspend fun register(paramsRegistration: ParamsRegistration): Flow<BaseResponse<UserModel>>
}