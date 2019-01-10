package at.fh.swengb.gruber.homeexercise2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var notesDb: NoteRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        notesDb = NoteRoomDatabase.getDatabase(applicationContext)
    }

    fun saveNote(v: View) {
        var noteTitle = note_title.text.toString()
        var noteContent = content.text.toString()
        var note = note(noteTitle,noteContent)
        notesDb.noteDao.insert(note)

        finish()
    }



}
