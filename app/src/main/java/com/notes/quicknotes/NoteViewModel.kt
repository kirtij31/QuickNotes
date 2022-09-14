package com.notes.quicknotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repository :NoteRepository
     val allNotes : LiveData<List<Note>>
     val tableSize : LiveData<Int>

    init{
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
         repository = NoteRepository(dao)
        allNotes = repository.allNotes
        tableSize = repository.tableSize
    }


    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteNote(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }


     fun update(text:String, title :String, id:Int) = viewModelScope.launch(Dispatchers.IO){
        repository.update(text,title,id)
    }

     fun deleteSelectedNote() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteSelected()
    }

    fun setSelectionStatusTrue(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.setSelectionStatusTrue(note)
    }

    fun setSelectionStatusFalse(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.setSelectionStatusFalse(note)
    }

    fun setSelectionStatusFalseForAll()= viewModelScope.launch(Dispatchers.IO){
        repository.setSelectionStatusFalseForAll()
    }
}