package vn.vnext.appcommon.presentation.ui.login

import vn.vnext.appcommon.presentation.base.ui.BaseViewState

sealed class LoginViewState {
    object InitState : LoginViewState()
    data class LoadingState(var isShowLoading: Boolean) : LoginViewState()
    data class ErrorState(var error: Throwable) : LoginViewState()
}