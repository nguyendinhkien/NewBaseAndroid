package vn.nguyendinhkien.appcommon.presentation.base.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.core.NetworkErrorException
import vn.nguyendinhkien.appcommon.presentation.SharedViewModel
import vn.nguyendinhkien.appcommon.utils.LoadingUtils
import vn.nguyendinhkien.appcommon.utils.safeNavigate

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
        view.setBackgroundColor(Color.WHITE)
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

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.navigation.collect { state ->
                    println("${viewModel.javaClass.simpleName} Navigation State: ${state.javaClass.simpleName}")
                    when (state) {
                        is NavigationCommand.ToDirection -> {
                            navController.safeNavigate(state.directions, state.navOptions)
                            viewModel.resetState()
                        }

                        is NavigationCommand.Back<*> -> {
                            if (state.key != null && state.data != null) {
                                returnData(state.key, state.data)
                            }
                            navController.popBackStack()
                        }

                        is NavigationCommand.ToLoginScreen -> {
                            val navOptions = navController.currentDestination?.id?.let {
                                NavOptions.Builder().setPopUpTo(
                                    it,
                                    inclusive = true,
                                    saveState = false
                                ).build()
                            }
                            navController.navigate(
                                resId = R.id.loginFragment,
                                args = null,
                                navOptions = navOptions
                            )
                        }

                        else -> {

                        }
                    }

                }
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