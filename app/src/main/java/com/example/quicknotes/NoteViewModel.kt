package com.example.quicknotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repository :NoteRepository
     val allNotes : LiveData<List<Note>>

    init{
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
         repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote() = viewModelScope.launch(Dispatchers.IO) {
        repository.delete()
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun setSelectionStatusTrue(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.setSelectionStatusTrue(note)
    }

    fun setSelectionStatusFalse(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.setSelectionStatusFalse(note)
    }
}