package com.notes.quicknotes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


lateinit var viewModel: NoteViewModel

  var isLongPressed = false
  var isPressed = false

lateinit var adapter : NotesRVAdapter



class MainActivity : AppCompatActivity() , INotesRVAdapter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager =    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this) { list ->
            list?.let { adapter.updateList(it) }

        }

        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.delete-> {
                    viewModel.deleteSelectedNote()
                    bottomNavigationView.visibility = View.GONE
                    floating_add.visibility = View.VISIBLE
                    viewModel.setSelectionStatusFalseForAll()
                    isLongPressed = false
                }
            }
            true
        }

    }

    fun add(view: View) {
        val intent = Intent(this, NewNote::class.java)
        startActivity(intent)
    }

    override fun onItemClicked(note: Note) {

        if(!isLongPressed){
        val intent = Intent(this,NewNote::class.java)
        intent.putExtra("text", note.text)
        intent.putExtra("title", note.title)
        intent.putExtra("id",note.id)
        startActivity(intent)
        }
        else if(isPressed){
            viewModel.setSelectionStatusFalse(note)
            isPressed = false
        }
        else{
            viewModel.setSelectionStatusTrue(note)
            isPressed = true
        }


    }

    override fun onItemLongClicked(note: Note) {
        isLongPressed = true
        isPressed = true
        viewModel.setSelectionStatusTrue(note)
        bottomNavigationView.visibility = View.VISIBLE
        floating_add.visibility = View.GONE
    }

    override fun onRestart() {
        super.onRestart()
        adapter.notifyDataSetChanged()
    }


}
