package com.example.quicknotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_note.*
import kotlinx.android.synthetic.main.new_note.*


lateinit var viewModel: NoteViewModel


class MainActivity : AppCompatActivity() , INotesRVAdapter{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager =    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let{adapter.updateList(it)}

        })

        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.delete-> {
                    viewModel.deleteNote()
                    bottomNavigationView.visibility = View.GONE
                    floating_add.visibility = View.VISIBLE
                }
            }
            true
        }

    }

    fun Add(view: View) {
        val intent = Intent(this,NewNote::class.java)
        startActivity(intent)
    }

    override fun onItemClicked(note: Note) {
        bottomNavigationView.visibility = View.VISIBLE
        floating_add.visibility = View.GONE
        viewModel.setSelectionStatusTrue(note)


    }


}
