package vn.vnext.appcommon.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import vn.vnext.appcommon.presentation.base.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : BaseViewModel<Nothing>() {
}