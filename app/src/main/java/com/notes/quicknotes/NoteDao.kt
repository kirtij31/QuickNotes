package com.notes.quicknotes

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Delete

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Query("DELETE FROM notes_table WHERE id = :id")
    suspend fun delete(id:Int)

    @Query("UPDATE notes_table SET text=:text, title = :title WHERE id = :id")
    suspend fun update(text:String, title :String, id:Int)


    @Query("DELETE FROM notes_table WHERE selected ='true' ")
    suspend fun deleteSelected()

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getALLNoteS():LiveData<List<Note>>

    @Query("SELECT COUNT(*) FROM notes_table")
    fun tableSize():LiveData<Int>


    @Query("UPDATE notes_table SET selected = 'true' WHERE id = :id")
    suspend fun setSelectionStatusTrue( id: Int)

    @Query("UPDATE notes_table SET selected = 'false' WHERE id = :id")
    suspend fun setSelectionStatusFalse( id: Int)

    @Query("UPDATE notes_table SET selected = 'false' ")
    suspend fun setSelectionStatusFalseForAll()
}