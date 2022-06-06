package vn.vnext.appcommon.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import dagger.hilt.android.AndroidEntryPoint
import vn.vnext.appcommon.R
import vn.vnext.appcommon.databinding.FragmentHomeBinding
import vn.vnext.appcommon.presentation.base.ui.BaseFragment
import vn.vnext.appcommon.presentation.ui.login.LoginFragmentDirections

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewState, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModels()
    override fun onViewReady(savedInstanceState: Bundle?) {
        onSuccessState = { state ->
            when (state) {
                is HomeViewState.AuthenticationState -> {
                    if (!state.isAuthenticated) {
                        navigateLoginScreen()
                    }
                }
            }
        }

        binding.apply {
            buttonLogout.setOnClickListener {
                viewModel.logout()
            }
        }
    }

    private fun navigateLoginScreen() {
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.homeFragment, true)
        val navDirections = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        viewModel.navigate(navDirections, navOptions)
    }
}