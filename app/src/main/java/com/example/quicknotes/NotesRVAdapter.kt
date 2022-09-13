package com.example.quicknotes

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.*

class NotesRVAdapter(val context: Context, val iNotesRVAdapter: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {

 val allNotes = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))


        viewHolder.cardView.setOnLongClickListener {
            viewHolder.cardView.setCardBackgroundColor(Color.parseColor("#E4E4DE"))

            iNotesRVAdapter.onItemClicked(allNotes[viewHolder.adapterPosition])
            true
        }

//        viewHolder.cardView.setOnClickListener{
//            if(allNotes[viewHolder.adapterPosition].selected == true)
//                viewHolder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
//
//            iNotesRVAdapter.onItemClicked(allNotes[viewHolder.adapterPosition])
//        }

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
        val cardView = itemView.findViewById<CardView>(R.id.item_layout)
    }
}

interface INotesRVAdapter{
    fun onItemClicked(note: Note)
}