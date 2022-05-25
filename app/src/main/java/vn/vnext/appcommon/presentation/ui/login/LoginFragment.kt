package vn.vnext.appcommon.presentation.ui.login


import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import dagger.hilt.android.AndroidEntryPoint
import vn.vnext.appcommon.R
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.databinding.FragmentLoginBinding
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.presentation.base.ui.BaseFragment
import vn.vnext.appcommon.utils.stateFlowCollect

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel by viewModels()

    private val validation: AwesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)

    override fun onViewReady(savedInstanceState: Bundle?) {
        binding.apply {

            validation.addValidation(
                tilUsername,
                AppConstants.USERNAME_REGEX,
                getString(R.string.text_error_username_invalid)
            )

            validation.addValidation(
                tilPassword,
                AppConstants.PASSWORD_REGEX,
                getString(R.string.text_error_password_invalid)
            )

            buttonLogin.setOnClickListener {
                if (validation.validate()) {
                    viewModel.login(
                        ParamsLogin(
                            tilUsername.editText!!.text.toString(),
                            tilPassword.editText!!.text.toString()
                        )
                    )
                }
            }

            buttonRegister.setOnClickListener {
                viewModel.navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
                validation.clear()
            }
        }

        stateFlowCollect(viewModel.uiState, this::collection)

    }

    private fun collection(state: LoginViewState){
        when (state) {
            is LoginViewState.LoadingState -> {
                showLoading(state.isShowLoading)
            }
            is LoginViewState.ErrorState -> {
                showError(state.error)
            }
            else -> {

            }
        }
    }

}