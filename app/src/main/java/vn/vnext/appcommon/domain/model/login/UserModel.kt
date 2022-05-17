package vn.vnext.appcommon.domain.model.login

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("token")
    var token: String? = null,

    @SerializedName("reason")
    var reason: String? = null
)
