package vn.vnext.appcommon.data.repository

import kotlinx.coroutines.flow.Flow
import vn.vnext.appcommon.core.BaseResponse
import vn.vnext.appcommon.data.source.remote.IAuthenticationApi
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.model.login.UserModel
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.domain.repository.IAuthenticationRepository
import vn.vnext.appcommon.utils.NetworkUtils
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val api: IAuthenticationApi,
    private val prefsHelper: PrefsHelper
) : IAuthenticationRepository {
    override suspend fun login(paramsLogin: ParamsLogin): Flow<BaseResponse<UserModel>> {

        val flowLogin = NetworkUtils.safeCallApi {
            api.login(paramsLogin)
        }
        return flowLogin
    }
}