package vn.vnext.appcommon.presentation.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vn.vnext.appcommon.databinding.FragmentLoginBinding
import vn.vnext.appcommon.domain.model.login.ParamsLogin
import vn.vnext.appcommon.presentation.base.ui.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    override fun onViewReady(savedInstanceState: Bundle?) {

        viewModel.login(ParamsLogin("admin", "password123"))
    }

}