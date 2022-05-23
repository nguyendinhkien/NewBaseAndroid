package vn.vnext.appcommon.presentation.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.presentation.base.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val prefsHelper: PrefsHelper
) : BaseViewModel() {

    fun logout() {
        prefsHelper.remove(AppConstants.TOKEN)
        _uiState.value = HomeViewState.AuthenticationState(false)
    }

    private val _uiState = MutableStateFlow<HomeViewState>(HomeViewState.InitState)
    val uiState = _uiState.asStateFlow()

    init {
        val accessToken = prefsHelper.readString(AppConstants.TOKEN, null)
        if (accessToken == null) {
            _uiState.value = HomeViewState.AuthenticationState(false)
        } else {
            _uiState.value = HomeViewState.AuthenticationState(true)
        }
    }
}