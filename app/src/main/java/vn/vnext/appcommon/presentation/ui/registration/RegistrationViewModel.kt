package vn.vnext.appcommon.presentation.ui.registration

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vn.vnext.appcommon.R
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.core.BaseResponse
import vn.vnext.appcommon.domain.model.registration.ParamsRegistration
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.domain.usecase.authenication.RegistrationUseCase
import vn.vnext.appcommon.presentation.base.ui.BaseState
import vn.vnext.appcommon.presentation.base.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registration: RegistrationUseCase,
) : BaseViewModel<RegistrationViewState>() {

//    private val _uiState = MutableStateFlow<RegistrationViewState>(RegistrationViewState.InitState)
//    val uiState = _uiState.asStateFlow()

    fun registerUser(paramsRegistration: ParamsRegistration) {
        viewModelScope.launch {
            registration(paramsRegistration)
                .onStart {
                    _uiState.update { BaseState.LoadingState(true) }
                }
                .collect { response ->
                    _uiState.update { BaseState.LoadingState(false) }

                    when (response) {
                        is BaseResponse.Failure -> {
                            _uiState.update { BaseState.FailureState(response.error) }
                        }
                        is BaseResponse.Success -> {
                            navigateToHomeScreen()
                        }
                    }
                }
        }
    }

    private fun navigateToHomeScreen() {
        val navDirections =
            RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
        val navOptionsBuilder = NavOptions.Builder()
            .setPopUpTo(R.id.registrationFragment, true)
        navigate(navDirections, navOptionsBuilder)
    }
}