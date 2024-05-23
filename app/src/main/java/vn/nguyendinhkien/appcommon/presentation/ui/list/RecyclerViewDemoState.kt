package vn.nguyendinhkien.appcommon.presentation.ui.list

import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseState

sealed class RecyclerViewDemoState{
    data class SuccessState(val values: List<Note>) : RecyclerViewDemoState()
}