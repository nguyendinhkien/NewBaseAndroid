package vn.nguyendinhkien.appcommon.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import dagger.hilt.android.AndroidEntryPoint
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.databinding.FragmentHomeBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseFragment
import vn.nguyendinhkien.appcommon.presentation.ui.list.ListViewAdapter
import javax.inject.Inject

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