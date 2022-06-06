package vn.vnext.appcommon.presentation.ui.home

sealed class HomeViewState{
//    object InitState: HomeViewState()
    data class AuthenticationState(val isAuthenticated: Boolean): HomeViewState()
}
