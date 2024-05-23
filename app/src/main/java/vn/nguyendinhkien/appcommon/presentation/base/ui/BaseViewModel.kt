package vn.nguyendinhkien.appcommon.presentation.base.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import vn.nguyendinhkien.appcommon.R
import vn.nguyendinhkien.appcommon.core.AppConstants
import vn.nguyendinhkien.appcommon.domain.preferences.PrefsHelper
import kotlin.coroutines.CoroutineContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel<T : Any> : ViewModel(), CoroutineScope, KoinComponent {

    val prefs: PrefsHelper by inject()

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + job
    private val job: Job = SupervisorJob()

    private val _navigation = MutableStateFlow<NavigationCommand>(NavigationCommand.StayHere)
    val navigation = _navigation.asStateFlow()

    protected val _uiState = MutableStateFlow<BaseState<T>>(BaseState.InitState)
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


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
        val navOptions: NavOptions
    ) : NavigationCommand()

    data class Back<T>(val key: String? = null, val data: T? = null) : NavigationCommand()
}

class QuickState {}

class QuickViewModel(): BaseViewModel<QuickState>(){

}