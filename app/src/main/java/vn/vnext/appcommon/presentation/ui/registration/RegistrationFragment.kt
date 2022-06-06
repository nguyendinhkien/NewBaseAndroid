package vn.vnext.appcommon.presentation.ui.registration

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import dagger.hilt.android.AndroidEntryPoint
import vn.vnext.appcommon.R
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.databinding.FragmentRegistrationBinding
import vn.vnext.appcommon.domain.model.registration.ParamsRegistration
import vn.vnext.appcommon.presentation.base.ui.BaseFragment
import vn.vnext.appcommon.presentation.ui.login.LoginViewState
import vn.vnext.appcommon.utils.stateFlowCollect

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding, RegistrationViewState, RegistrationViewModel>(
        FragmentRegistrationBinding::inflate
    ) {
    override val viewModel: RegistrationViewModel by viewModels()

    private val validation: AwesomeValidation = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)

    override fun onViewReady(savedInstanceState: Bundle?) {

        binding.apply {
            buttonLogin.setOnClickListener {
                viewModel.popBack()
            }

            validation.clear()

            validation.addValidation(
                tilEmail,
                AppConstants.EMAIL_REGEX,
                getString(R.string.text_error_email_invalid)
            )
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
            validation.addValidation(
                tilRePassword,
                AppConstants.PASSWORD_REGEX,
                getString(R.string.text_error_re_password_invalid)
            )
            validation.addValidation(
                tilRePassword,
                { input -> input.equals(tilPassword.editText!!.text.toString()) },
                getString(R.string.text_error_re_password_not_match)
            )

            buttonRegister.setOnClickListener {
                if (validation.validate()) {
                    viewModel.registerUser(
                        ParamsRegistration(
                            tilUsername.editText!!.text.toString(),
                            tilPassword.editText!!.text.toString(),
                            tilRePassword.editText!!.text.toString()
                        )
                    )
                }
            }
        }
    }
}