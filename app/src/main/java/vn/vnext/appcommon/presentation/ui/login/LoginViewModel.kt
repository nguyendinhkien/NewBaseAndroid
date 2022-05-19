package vn.vnext.appcommon.presentation.ui.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vn.vnext.appcommon.core.BaseResponse
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.domain.usecase.authenication.LoginUseCase
import vn.vnext.appcommon.presentation.base.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val prefsHelper: PrefsHelper
) : BaseViewModel() {
    fun login(paramsLogin: ParamsLogin) {

        viewModelScope.launch {
            loginUseCase(paramsLogin).collect { response ->
                when (response) {
                    is BaseResponse.Success -> {
                        if (response.data.token != null) {
                            prefsHelper.saveToString("token", response.data.token!!)
                        }
                    }

                    is BaseResponse.Failure -> {
                        println("failure ${response.error.message}")
                    }
                }
            }
        }
    }
}