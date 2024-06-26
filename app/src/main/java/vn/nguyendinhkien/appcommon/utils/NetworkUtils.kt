package vn.nguyendinhkien.appcommon.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import vn.nguyendinhkien.appcommon.core.BaseResponse
import vn.nguyendinhkien.appcommon.core.NetworkErrorException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkUtils {
    companion object {
        private fun resolveError(e: Throwable): Throwable {
            var error = e
            when (e) {
                is SocketTimeoutException -> {
                    error = NetworkErrorException(errorMessage = "Connection error")
                }
                is ConnectException -> {
                    error = NetworkErrorException(errorMessage = "No internet access")
                }
                is UnknownHostException -> {
                    error = NetworkErrorException(errorMessage = "No internet access")
                }
                is HttpException -> {
                    error = when (e.code()) {
                        502 -> {
                            NetworkErrorException(e.code(), "Internal error")
                        }
                        404 -> {
                            NetworkErrorException(e.code(), "Server is maintenance")
                        }
                        else -> {
                            NetworkErrorException.parseException(e)
                        }
                    }
                }
            }
            return error
        }

        suspend fun <T : Any> safeCallApi(
            networkApiCall: suspend () -> Response<T>
        ): Flow<BaseResponse<T>> {
            return flow {
                val response = networkApiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(BaseResponse.Success(it))

                    }
                        ?: emit(BaseResponse.Failure(NetworkErrorException(errorMessage = "Empty response body")))
                    return@flow
                }
                emit(
                    BaseResponse.Failure(
                        NetworkErrorException(
                            errorCode = response.code(),
                            errorMessage = "API call failed with error: " + response.message()
                        )
                    )
                )
            }.catch { e ->
                emit(BaseResponse.Failure(resolveError(e)))
                return@catch
            }.flowOn(Dispatchers.IO)
        }

        suspend fun <T : Any> safeCallApiWithAction(
            networkApiCall: suspend () -> Response<T>,
            action: suspend (T) -> Unit
        ): Flow<BaseResponse<T>> {
            return flow {
                val response = networkApiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(BaseResponse.Success(it))
                        action(it)
                    }
                        ?: emit(BaseResponse.Failure(NetworkErrorException(errorMessage = "Empty response body")))
                    return@flow
                }
                emit(
                    BaseResponse.Failure(
                        NetworkErrorException(
                            errorCode = response.code(),
                            errorMessage = response.message()
                        )
                    )
                )

            }.catch { e ->
                emit(BaseResponse.Failure(resolveError(e)))
                return@catch
            }.flowOn(Dispatchers.IO)
        }
    }
}