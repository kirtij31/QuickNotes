package com.example.quicknotes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao :NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getALLNoteS()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete() {
        noteDao.delete()
    }

    suspend fun setSelectionStatusTrue(note: Note) {
        noteDao.setSelectionStatusTrue(note.id)
    }

    suspend fun setSelectionStatusFalse(note: Note) {
        noteDao.setSelectionStatusFalse(note.id)
    }
}