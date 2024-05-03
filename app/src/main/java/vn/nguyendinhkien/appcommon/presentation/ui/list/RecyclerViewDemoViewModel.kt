package vn.nguyendinhkien.appcommon.presentation.ui.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.nguyendinhkien.appcommon.domain.usecase.notes.NotesUseCase
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseState
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RecyclerViewDemoViewModel @Inject constructor(
    private val notesUseCase: NotesUseCase
) :
    BaseViewModel<List<Note>>() {

    private fun fetchNotes() {
        _uiState.update { BaseState.LoadingState(isShowLoading = true) }
        viewModelScope.launch {
            notesUseCase().collect { data ->
                    _uiState.update { BaseState.LoadingState(isShowLoading = false) }
                    _uiState.update { BaseState.SuccessState(data) }
                }
        }
    }


    init {
        println("viewmodel init")
        fetchNotes()
    }
}