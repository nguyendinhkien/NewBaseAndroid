package vn.vnext.appcommon.domain.model.registration

import com.google.gson.annotations.SerializedName

data class ParamsRegistration(

    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("confirmPassword")
    val confirmPassword: String
)
