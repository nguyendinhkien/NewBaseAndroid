package vn.nguyendinhkien.appcommon.presentation.ui.login

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.core.BaseResponse
import vn.nguyendinhkien.appcommon.core.NetworkErrorException
import vn.nguyendinhkien.appcommon.domain.model.login.ParamsLogin
import vn.nguyendinhkien.appcommon.domain.usecase.authenication.LoginUseCase
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseState
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseViewModel

class LoginViewModel(
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