package com.notes.quicknotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text") var text:String, var title:String, var selected: Boolean) {


    @PrimaryKey (autoGenerate = true)
    var id = 0

}