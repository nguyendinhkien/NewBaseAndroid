package vn.nguyendinhkien.appcommon.domain.usecase.notes

import vn.nguyendinhkien.appcommon.domain.repository.INoteRepository
class NotesUseCase(
    private val noteRepository: INoteRepository
) {
    suspend operator fun invoke() = noteRepository.fetchNotes()
}