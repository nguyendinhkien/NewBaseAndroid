package vn.vnext.appcommon.presentation.ui.login

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import vn.vnext.appcommon.R
import vn.vnext.appcommon.core.BaseResponse
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.core.NetworkErrorException
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.domain.usecase.authenication.LoginUseCase
import vn.vnext.appcommon.presentation.base.ui.BaseState
import vn.vnext.appcommon.presentation.base.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginViewState>() {

    fun login(paramsLogin: ParamsLogin) {
        viewModelScope.launch {
            loginUseCase(paramsLogin)
                .onStart {
                    _uiState.value = BaseState.LoadingState(true)
                }
                .collect { response ->
                    _uiState.value = BaseState.LoadingState(false)
                    when (response) {
                        is BaseResponse.Success -> {
                            if (response.data.token != null) {
                                navigateHomeScreen()
                            } else {
                                _uiState.value = BaseState.FailureState(
                                    NetworkErrorException(
                                        errorMessage = response.data.reason ?: ""
                                    )
                                )
                            }
                        }

                        is BaseResponse.Failure -> {
                            _uiState.value = BaseState.FailureState(response.error)
                        }
                    }
                }
        }
    }

    private fun navigateHomeScreen() {
        val navOptions: NavOptions.Builder = NavOptions.Builder()
            .setPopUpTo(R.id.loginFragment, true)
        val navDirections =
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        navigate(navDirections, navOptions)
    }
}