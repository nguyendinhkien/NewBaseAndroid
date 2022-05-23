package vn.vnext.appcommon.presentation.base.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import vn.vnext.appcommon.R

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableStateFlow<NavigationCommand>(NavigationCommand.StayHere)
    val navigation = _navigation.asStateFlow()

    fun navigate(navDirections: NavDirections, navOptionsBuilder: NavOptions.Builder? = null) {
        val builder: NavOptions.Builder = navOptionsBuilder ?: NavOptions.Builder()
        builder.setEnterAnim(R.anim.slide_left)
            .setExitAnim(R.anim.wait_anim)
            .setPopEnterAnim(R.anim.wait_anim)
            .setPopExitAnim(R.anim.slide_right)
        _navigation.update { NavigationCommand.ToDirection(navDirections, builder.build()) }
    }

    fun popBack() {
        _navigation.update { NavigationCommand.Back }
    }

    fun resetState() {
        _navigation.update { NavigationCommand.StayHere }
    }
}

sealed class NavigationCommand {
    object StayHere : NavigationCommand()
    data class ToDirection(
        val directions: NavDirections,
        val navOptions: NavOptions? = null
    ) :
        NavigationCommand()

    object Back : NavigationCommand()
}