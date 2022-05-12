package vn.vnext.appcommon.domain.preferences

interface PrefsHelper {
    fun saveToString(prefName: String, prefValue: String)


    fun saveToBoolean(prefName: String, prefValue: Boolean)

    fun saveToInteger(prefName: String, prefValue: Int)

    fun readString(prefName: String, defValue: String): String

    fun readBoolean(prefName: String, defValue: Boolean): Boolean

    fun readInteger(prefName: String, defValue: Int): Int

    fun remove(prefName: String)
}