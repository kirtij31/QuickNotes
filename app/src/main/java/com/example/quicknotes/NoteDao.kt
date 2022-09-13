package com.example.quicknotes

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Delete

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)


    @Query("DELETE FROM notes_table WHERE selected ='true' ")
    suspend fun delete()

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getALLNoteS():LiveData<List<Note>>


    @Query("UPDATE notes_table SET selected = 'true' WHERE id = :id")
    suspend fun setSelectionStatusTrue( id: Int)

    @Query("UPDATE notes_table SET selected = 'false' WHERE id = :id")
    suspend fun setSelectionStatusFalse( id: Int)
}