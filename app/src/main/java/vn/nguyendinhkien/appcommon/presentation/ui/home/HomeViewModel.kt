package vn.nguyendinhkien.appcommon.presentation.ui.home

import vn.nguyendinhkien.appcommon.core.AppConstants
import vn.nguyendinhkien.appcommon.domain.preferences.PrefsHelper
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseViewModel

class HomeViewModel(prefsHelper: PrefsHelper) : BaseViewModel<HomeViewState>() {

    fun logout() {
        accessTokenExpired()
    }

    fun navigateWebViewScreen() {
        val navDirections = HomeFragmentDirections.actionHomeFragmentToRecyclerViewDemoFragment()
        navigate(navDirections)
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