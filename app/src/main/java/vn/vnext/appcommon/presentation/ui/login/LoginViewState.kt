package vn.vnext.appcommon.presentation.ui.login

sealed class LoginViewState {
    data class LoadingState(var isShowLoading: Boolean) : LoginViewState()
    data class ErrorState(var error: Throwable) : LoginViewState()
    data class SuccessState(var isLoginSuccess: Boolean) : LoginViewState()
}