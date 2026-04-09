package com.techne.securenotes.domain.use_case

import com.techne.securenotes.domain.model.Note
import com.techne.securenotes.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}
