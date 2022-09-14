package com.notes.quicknotes

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val iNotesRVAdapter: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {

 private val allNotes = ArrayList<Note>()

    private var isPressed = false
    private var isLongPressed = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))


        viewHolder.cardView.setOnLongClickListener {
            isLongPressed = true
            isPressed = true
            viewHolder.cardView.setCardBackgroundColor(Color.parseColor("#E4E4DE"))

            iNotesRVAdapter.onItemLongClicked(allNotes[viewHolder.adapterPosition])

            true
        }

        viewHolder.cardView.setOnClickListener{
            if(isLongPressed){
                isPressed = if(isPressed){
                    viewHolder.cardView.setCardBackgroundColor(Color.WHITE)
                    false
                } else{
                    viewHolder.cardView.setCardBackgroundColor(Color.parseColor("#E4E4DE"))
                    true
                }
            }

            iNotesRVAdapter.onItemClicked(allNotes[viewHolder.adapterPosition])
        }



        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]

        val subNoteText = if(currentNote.text.length>45)
            currentNote.text.substring(0,45) + "..."
        else
            currentNote.text


        val subNoteTitle = if(currentNote.title.length>15)
            currentNote.title.substring(0,15) + "..."
        else
            currentNote.title

        holder.text.text = subNoteText
        holder.title.text = subNoteTitle

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
        val text: TextView = itemView.findViewById(R.id.note_text)
        val title: TextView = itemView.findViewById(R.id.note_title)
        val cardView: CardView = itemView.findViewById(R.id.item_layout)
    }
}

interface INotesRVAdapter{
    fun onItemClicked(note: Note)
    fun onItemLongClicked(note: Note)
}