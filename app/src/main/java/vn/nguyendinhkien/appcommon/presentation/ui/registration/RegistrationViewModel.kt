package vn.nguyendinhkien.appcommon.presentation.ui.registration

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.core.BaseResponse
import vn.nguyendinhkien.appcommon.domain.model.registration.ParamsRegistration
import vn.nguyendinhkien.appcommon.domain.usecase.authenication.RegistrationUseCase
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseState
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseViewModel

class RegistrationViewModel(
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