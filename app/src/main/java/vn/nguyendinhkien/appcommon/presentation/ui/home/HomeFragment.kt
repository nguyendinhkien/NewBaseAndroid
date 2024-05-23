package vn.nguyendinhkien.appcommon.presentation.ui.home

import android.os.Bundle
import org.koin.android.ext.android.inject
import vn.nguyendinhkien.appcommon.databinding.FragmentHomeBinding
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseFragment


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewState, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by inject()
    override fun onViewReady(savedInstanceState: Bundle?) {
        onSuccessState = {state ->
            when(state){
                is HomeViewState.AuthenticationState -> {
                    if (!state.isAuthenticated) {
                        viewModel.logout()
                    }


                }

            }
        }

        binding.apply {
            buttonLogout.setOnClickListener {
                viewModel.logout()
            }
            buttonWebView.setOnClickListener {
                viewModel.navigateWebViewScreen()
            }
        }
    }


}