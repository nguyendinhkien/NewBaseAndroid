package vn.vnext.appcommon.presentation.base.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.*
import vn.vnext.appcommon.R
import vn.vnext.appcommon.core.AppConstants
import vn.vnext.appcommon.domain.preferences.PrefsHelper
import javax.inject.Inject

abstract class BaseViewModel<T : Any> : ViewModel() {

    @Inject
    lateinit var prefs: PrefsHelper

    private val _navigation = MutableStateFlow<NavigationCommand>(NavigationCommand.StayHere)
    val navigation = _navigation.asStateFlow()

    protected val _uiState = MutableStateFlow<BaseState<T>>(BaseState.InitState)
    val uiState = _uiState.asStateFlow()

    fun navigate(
        navDirections: NavDirections,
        navOptionsBuilder: NavOptions.Builder? = null
    ) {
        val builder: NavOptions.Builder = navOptionsBuilder ?: NavOptions.Builder()
        builder.setEnterAnim(R.anim.slide_left)
            .setExitAnim(R.anim.wait_anim)
            .setPopEnterAnim(R.anim.wait_anim)
            .setPopExitAnim(R.anim.slide_right)
        _navigation.update { NavigationCommand.ToDirection(navDirections, builder.build()) }
    }

    fun popBack() {
        _navigation.update { NavigationCommand.Back(null, null) }
    }

    fun <T> popBackWithData(key: String, data: T) {
        _navigation.update { NavigationCommand.Back(key, data) }
    }

    fun resetState() {
        _navigation.update { NavigationCommand.StayHere }
    }

    protected fun setUiSuccessState(value: T) {
        _uiState.update { BaseState.SuccessState(value) }
    }

    fun accessTokenExpired() {
        prefs.remove(AppConstants.PREF_KEY_ACCESS_TOKEN)
        _navigation.update { NavigationCommand.ToLoginScreen }
    }

}

sealed class NavigationCommand {
    object StayHere : NavigationCommand()
    object ToLoginScreen : NavigationCommand()
    data class ToDirection(
        val directions: NavDirections,
        val navOptions: NavOptions? = null
    ) : NavigationCommand()

    data class Back<T>(val key: String? = null, val data: T? = null) : NavigationCommand()
}