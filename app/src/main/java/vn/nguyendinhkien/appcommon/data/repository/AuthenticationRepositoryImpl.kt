package vn.nguyendinhkien.appcommon.data.repository

import kotlinx.coroutines.flow.Flow
import vn.nguyendinhkien.appcommon.core.AppConstants
import vn.nguyendinhkien.appcommon.core.BaseResponse
import vn.nguyendinhkien.appcommon.data.source.remote.IAuthenticationApi
import vn.nguyendinhkien.appcommon.domain.model.login.ParamsLogin
import vn.nguyendinhkien.appcommon.domain.model.login.UserModel
import vn.nguyendinhkien.appcommon.domain.model.registration.ParamsRegistration
import vn.nguyendinhkien.appcommon.domain.preferences.PrefsHelper
import vn.nguyendinhkien.appcommon.domain.repository.IAuthenticationRepository
import vn.nguyendinhkien.appcommon.utils.NetworkUtils
class AuthenticationRepositoryImpl(
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
                    prefsHelper.saveToString(AppConstants.PREF_KEY_ACCESS_TOKEN, response.token!!)
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