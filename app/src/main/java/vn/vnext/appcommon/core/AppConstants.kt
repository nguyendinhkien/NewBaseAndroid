package vn.vnext.appcommon.core

class AppConstants {
    companion object{
        //Regex
        const val USERNAME_REGEX = "^[a-z0-9_-]{3,15}$"
        const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
        const val EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
        //Key
        const val TOKEN = "USER_ACCESS_TOKEN"
    }
}