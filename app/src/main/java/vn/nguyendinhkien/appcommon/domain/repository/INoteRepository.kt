package vn.nguyendinhkien.appcommon.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.nguyendinhkien.appcommon.core.BaseResponse
import vn.nguyendinhkien.appcommon.presentation.ui.list.Note

interface INoteRepository {
    suspend fun fetchNotes(): Flow<List<Note>>
}