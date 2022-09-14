package com.notes.quicknotes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao :NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getALLNoteS()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(id:Int){
        noteDao.delete(id)
    }


    suspend fun update(text:String, title :String, id:Int){
        noteDao.update(text,title,id)
    }

    suspend fun deleteSelected() {
        noteDao.deleteSelected()
    }

    suspend fun setSelectionStatusTrue(note: Note) {
        noteDao.setSelectionStatusTrue(note.id)
    }

    suspend fun setSelectionStatusFalse(note: Note) {
        noteDao.setSelectionStatusFalse(note.id)
    }

    suspend fun  setSelectionStatusFalseForAll(){
        noteDao.setSelectionStatusFalseForAll()
    }

}