package com.notes.quicknotes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_note.*


class NewNote : AppCompatActivity() {

    private var isNew :Boolean = true
    private lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_note)


        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = ""

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }



        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]


        val text = intent.getStringExtra("text")
        val title = intent.getStringExtra("title")

        if (text != null|| title!=null) {
            isNew = false
            input.setText(text)
            inputTitle.setText(title)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteOneNote -> {
                val id = intent.getIntExtra("id",0)
                   viewModel.deleteNote(id)
                    val intent = Intent(this, MainActivity::class.java)
                   startActivity(intent)
        }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onPause() {
        super.onPause()
        val noteText = input.text.toString()
        val noteTitle = inputTitle.text.toString()
        val id = intent.getIntExtra("id",0)

        if(isNew){
             if(noteText.isNotEmpty()|| noteTitle.isNotEmpty()){
            viewModel.insertNote(Note(noteText,noteTitle, false))
             }
            isNew = false
         }

        else{
            if(noteText.isEmpty()&& noteTitle.isEmpty())
             viewModel.deleteNote(id)
            else
                viewModel.update(noteText,noteTitle,id)
        }
    }

}