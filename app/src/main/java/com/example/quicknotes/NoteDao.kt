package com.example.quicknotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getALLNoteS():LiveData<List<Note>>
}