package com.example.quicknotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(val context: Context, val listener :INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {

 val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
          listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.note_text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.btn_delete)
    }
}

interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}