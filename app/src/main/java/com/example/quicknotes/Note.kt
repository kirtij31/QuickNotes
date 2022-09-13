package com.example.quicknotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Boolean.FALSE

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text") var text:String, var selected: Boolean) {


    @PrimaryKey (autoGenerate = true)
    var id = 0

}