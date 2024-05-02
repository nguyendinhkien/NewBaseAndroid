package vn.nguyendinhkien.appcommon.core

sealed class BaseResponse<out T> {
    data class Failure(var error: Throwable) : BaseResponse<Nothing>()
    data class Success<T>(var data: T) : BaseResponse<T>()
}