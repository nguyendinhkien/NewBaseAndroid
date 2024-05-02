package vn.nguyendinhkien.appcommon.data.source.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import vn.nguyendinhkien.appcommon.domain.model.login.ParamsLogin
import vn.nguyendinhkien.appcommon.domain.model.login.UserModel
import vn.nguyendinhkien.appcommon.domain.model.registration.ParamsRegistration

interface IAuthenticationApi {
    @POST("auth")
    suspend fun login(@Body paramsLogin: ParamsLogin): Response<UserModel>

    @POST("register")
    suspend fun register(@Body paramsRegistration: ParamsRegistration): Response<UserModel>
}