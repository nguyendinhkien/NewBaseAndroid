package vn.vnext.appcommon.core

import org.json.JSONObject
import retrofit2.HttpException

class NetworkErrorException(
    val errorCode: Int = -1,
    private val errorMessage: String,
) : Exception() {
    override val message: String
        get() = localizedMessage

    override fun getLocalizedMessage(): String {
        return errorMessage
    }

    companion object {
        fun parseException(e: HttpException): NetworkErrorException {
            val errorBody = e.response()?.errorBody()?.string()
            return try {
                NetworkErrorException(e.code(), JSONObject(errorBody!!).getString("message"))
            } catch (_: Exception) {
                NetworkErrorException(e.code(), "Unexpected Error")
            }
        }
    }
}