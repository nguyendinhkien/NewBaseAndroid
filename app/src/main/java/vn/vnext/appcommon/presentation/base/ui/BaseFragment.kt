package vn.vnext.appcommon.presentation.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import vn.vnext.appcommon.R
import vn.vnext.appcommon.core.NetworkErrorException
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import vn.vnext.appcommon.presentation.SharedViewModel
import vn.vnext.appcommon.utils.LoadingUtils
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding, T : Any, VM : BaseViewModel<T>>(bindingFactory: (LayoutInflater) -> B) :
    Fragment() {

    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    private lateinit var navController: NavController

    protected abstract val viewModel: VM

    protected val activityViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        onViewReady(savedInstanceState)
        onNavigationStateCollection()
        onUiStateCollection()
    }

    protected var onSuccessState: (T) -> Unit = {}

    private fun onUiStateCollection() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { state ->
                when (state) {
                    is BaseState.LoadingState -> {
                        showLoading(state.isShowLoading)
                    }
                    is BaseState.FailureState -> {
                        val error = state.error
                        showError(error = error)
                        if (error is NetworkErrorException && error.errorCode == NetworkErrorException.ACCESS_TOKEN_EXPIRED) {
                            viewModel.accessTokenExpired()
                        }
                    }
                    is BaseState.SuccessState -> {
                        onSuccessState(state.value)
                    }
                    else -> {}
                }
            }
        }
    }


    private fun onNavigationStateCollection() {

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.navigation.collect { state ->
                println("Navigation State: ${state.javaClass.simpleName}")
                when (state) {
                    is NavigationCommand.ToDirection -> {
                        state.directions
                        navController.navigate(state.directions, state.navOptions)
                    }
                    is NavigationCommand.Back<*> -> {
                        if (state.key != null && state.data != null) {
                            returnData(state.key, state.data)
                        }
                        navController.popBackStack()
                    }
                    is NavigationCommand.ToLoginScreen -> {
                        navController.navigate(
                            R.id.loginFragment, null,
                            NavOptions.Builder().setPopUpTo(
                                navController.currentDestination!!.id,
                                inclusive = true,
                            ).build()
                        )
                    }
                    else -> {

                    }
                }
                viewModel.resetState()
            }
        }
    }

    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    private fun showLoading(showLoading: Boolean) {
        if (showLoading) {
            LoadingUtils.showLoading(context, false)
        } else {
            LoadingUtils.hideLoading()
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    protected fun <T> observeResultFromDestination(key: String, observeResult: (T) -> Unit): T? {
        var r: T? = null
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
            ?.observe(viewLifecycleOwner) { result ->
                r = result
                observeResult(result)
            }
        return r
    }

    private fun <T> returnData(key: String, result: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, result)
        /*
        If you’d only like to handle a result only once, you must call remove()
        //this.previousBackStackEntry?.savedStateHandle?.remove(key)
        */
    }
}