package vn.nguyendinhkien.appcommon.presentation.base.ui

sealed class BaseState<out T : Any> {
    object InitState : BaseState<Nothing>()
    data class LoadingState(val isShowLoading: Boolean) : BaseState<Nothing>()
    data class FailureState(val error: Throwable) : BaseState<Nothing>()
    data class SuccessState<out T : Any>(val value: T) : BaseState<T>()
}
