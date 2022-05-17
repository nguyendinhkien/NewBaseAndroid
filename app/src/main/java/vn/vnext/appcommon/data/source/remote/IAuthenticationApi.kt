package vn.vnext.appcommon.data.source.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.model.login.UserModel

interface IAuthenticationApi {
    @POST("auth")
    suspend fun login(@Body paramsLogin: ParamsLogin): Response<UserModel>
}