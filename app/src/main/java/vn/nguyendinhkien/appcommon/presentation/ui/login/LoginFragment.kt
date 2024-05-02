package vn.nguyendinhkien.appcommon.presentation.ui.login


import android.os.Bundle
import androidx.fragment.app.viewModels
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import dagger.hilt.android.AndroidEntryPoint
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.core.AppConstants
import vn.nguyendinhkien.appcommon.databinding.FragmentLoginBinding
import vn.nguyendinhkien.appcommon.domain.model.login.ParamsLogin
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseFragment

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewState, LoginViewModel>(FragmentLoginBinding::inflate) {

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
    }

}