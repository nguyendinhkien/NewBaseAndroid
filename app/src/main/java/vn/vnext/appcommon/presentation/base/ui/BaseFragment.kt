package vn.vnext.appcommon.presentation.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import vn.vnext.appcommon.R
import vn.vnext.appcommon.presentation.SharedViewModel
import vn.vnext.appcommon.utils.LoadingUtils

abstract class BaseFragment<B : ViewBinding, VM : BaseViewModel>(bindingFactory: (LayoutInflater) -> B) :
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
    }

    private fun onNavigationStateCollection() {

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.navigation.collect { state ->
                when (state) {
                    is NavigationCommand.ToDirection -> {

                        navController.navigate(state.directions, state.navOptions)
                        viewModel.resetState()
                    }
                    is NavigationCommand.Back -> {
                        navController.popBackStack()
                        viewModel.resetState()
                    }
                    else -> {

                    }
                }
            }
        }
    }

    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    protected fun showLoading(showLoading: Boolean) {
        if (showLoading) {
            LoadingUtils.showLoading(context, false)
        } else {
            LoadingUtils.hideLoading()
        }
    }

    protected fun showError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }
}