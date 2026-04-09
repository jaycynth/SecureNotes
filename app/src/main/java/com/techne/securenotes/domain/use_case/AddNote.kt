package com.techne.securenotes.domain.use_case

import com.techne.securenotes.domain.model.Note
import com.techne.securenotes.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw Exception("The title of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}
