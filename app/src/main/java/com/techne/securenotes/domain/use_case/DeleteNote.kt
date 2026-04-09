package com.techne.securenotes.domain.use_case

import com.techne.securenotes.domain.model.Note
import com.techne.securenotes.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
