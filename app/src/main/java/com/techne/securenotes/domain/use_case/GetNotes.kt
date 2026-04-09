package com.techne.securenotes.domain.use_case

import com.techne.securenotes.domain.model.Note
import com.techne.securenotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            notes.sortedByDescending { it.timestamp }
        }
    }
}
