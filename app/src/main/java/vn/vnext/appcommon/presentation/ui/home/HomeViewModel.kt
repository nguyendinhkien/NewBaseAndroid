package vn.vnext.appcommon.presentation.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.core.NetworkErrorException
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.presentation.base.ui.BaseState
import vn.vnext.appcommon.presentation.base.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val prefsHelper: PrefsHelper
) : BaseViewModel<HomeViewState>() {

    fun logout() {
        prefsHelper.remove(AppConstants.PREF_KEY_ACCESS_TOKEN)
        setUiSuccessState(HomeViewState.AuthenticationState(false))
    }

    init {
        val accessToken = prefsHelper.readString(AppConstants.PREF_KEY_ACCESS_TOKEN, null)
        if (accessToken == null) {
            setUiSuccessState(HomeViewState.AuthenticationState(false))
        } else {
            setUiSuccessState(HomeViewState.AuthenticationState(true))
        }
    }
}