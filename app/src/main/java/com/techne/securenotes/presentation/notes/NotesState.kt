package com.techne.securenotes.presentation.notes

import com.techne.securenotes.domain.model.Note

data class NotesState(
    val notes: List<Note> = emptyList()
)
