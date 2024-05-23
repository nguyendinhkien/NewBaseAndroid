package vn.nguyendinhkien.appcommon.presentation.ui.list

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vn.nguyendinhkien.appcommon.domain.usecase.notes.NotesUseCase
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseState
import vn.nguyendinhkien.appcommon.presentation.base.ui.BaseViewModel

class RecyclerViewDemoViewModel(
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