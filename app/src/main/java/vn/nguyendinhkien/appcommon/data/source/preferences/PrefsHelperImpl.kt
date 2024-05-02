package vn.nguyendinhkien.appcommon.data.source.preferences

import android.content.SharedPreferences
import vn.nguyendinhkien.appcommon.domain.preferences.PrefsHelper
import javax.inject.Inject

class PrefsHelperImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    PrefsHelper {
    private val editor = sharedPreferences.edit()
    override fun saveToString(prefName: String, prefValue: String) {
        editor.putString(prefName, prefValue)
        editor.apply()
    }

    override fun saveToBoolean(prefName: String, prefValue: Boolean) {
        editor.putBoolean(prefName, prefValue)
        editor.apply()
    }

    override fun saveToInteger(prefName: String, prefValue: Int) {
        editor.putInt(prefName, prefValue)
        editor.apply()
    }

    override fun readString(prefName: String, defValue: String?): String? {
        return sharedPreferences.getString(prefName, defValue) ?: defValue
    }

    override fun readBoolean(prefName: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(prefName, defValue)
    }

    override fun readInteger(prefName: String, defValue: Int): Int {
        return sharedPreferences.getInt(prefName, defValue)
    }

    override fun remove(prefName: String) {
        editor.remove(prefName)
        editor.apply()
    }
}