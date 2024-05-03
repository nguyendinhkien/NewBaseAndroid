package vn.nguyendinhkien.appcommon.domain.usecase.notes

import vn.nguyendinhkien.appcommon.domain.repository.INoteRepository
import javax.inject.Inject

class NotesUseCase @Inject constructor(
    private val noteRepository: INoteRepository
) {
    suspend operator fun invoke() = noteRepository.fetchNotes()
}