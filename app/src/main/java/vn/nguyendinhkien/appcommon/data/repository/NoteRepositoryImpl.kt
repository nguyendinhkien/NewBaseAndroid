package vn.nguyendinhkien.appcommon.data.repository

import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import vn.nguyendinhkien.appcommon.domain.repository.INoteRepository
import vn.nguyendinhkien.appcommon.presentation.ui.list.Note


class NoteRepositoryImpl(): INoteRepository {
    override suspend fun fetchNotes(): Flow<List<Note>> {
        coroutineScope {
            delay(5000)
        }

        return flowOf(
            listOf(
                Note(id = 1, name = "Kien 1"),
                Note(id = 2, name = "Kien 2"),
                Note(id = 3, name = "Kien 3"),
                Note(id = 4, name = "Kien 4"),
                Note(id = 5, name = "Kien 5"),
            )
        )
    }
}