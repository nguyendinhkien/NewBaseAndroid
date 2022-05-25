package vn.vnext.appcommon.data.repository

import kotlinx.coroutines.flow.Flow
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.core.BaseResponse
import vn.vnext.appcommon.data.source.remote.IAuthenticationApi
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.model.login.UserModel
import vn.vnext.appcommon.domain.model.registration.ParamsRegistration
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.domain.repository.IAuthenticationRepository
import vn.vnext.appcommon.utils.NetworkUtils
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val api: IAuthenticationApi,
    private val prefsHelper: PrefsHelper
) : IAuthenticationRepository {
    override suspend fun login(paramsLogin: ParamsLogin): Flow<BaseResponse<UserModel>> {
        return NetworkUtils.safeCallApiWithAction(
            networkApiCall = {
                api.login(paramsLogin)
            },
            action = { response ->
                if (response.token != null) {
                    prefsHelper.saveToString(AppConstants.TOKEN, response.token!!)
                }
            }
        )
    }

    override suspend fun register(paramsRegistration: ParamsRegistration): Flow<BaseResponse<UserModel>> {
        return NetworkUtils.safeCallApi {
            api.register(paramsRegistration)
        }
    }
}