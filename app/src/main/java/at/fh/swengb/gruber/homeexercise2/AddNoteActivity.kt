package at.fh.swengb.gruber.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import at.fh.swengb.gruber.homeexercise2.NoteListActivity.Companion.EXTRA_ID_KEY
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var notesDb: NoteRoomDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        notesDb = NoteRoomDatabase.getDatabase(applicationContext)
        val noteId = intent.getLongExtra(EXTRA_ID_KEY, 0)
        if (noteId>0) {
            val editNote = notesDb.noteDao.openNote(noteId)
            note_title.setText(editNote.title)
            content.setText(editNote.content)
        }
    }

    fun saveNote(v: View) {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)
        val noteTitle = note_title.text.toString()
        val noteContent = content.text.toString()
        val userId = notesDb.userDao.findUserId(username) ?: -1
        val note = note(noteTitle, noteContent, userId)

        if (noteTitle.isEmpty() ||
            noteContent.isEmpty()
        ) {
            val text = "Please enter a Title and a Content!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            return
        }

        val noteId = intent.getLongExtra(EXTRA_ID_KEY, 0)
        if (noteId <= 0) {
            notesDb.noteDao.insert(note)
        }else{
            notesDb.noteDao.updateNote(noteTitle, noteContent, noteId)
        }

        finish()
    }

    fun shareNote(v: View) {
        val noteContent = content.text.toString()
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, noteContent)
        intent.type = "text/plain"
        val chooserIntent = Intent.createChooser(intent, "Select an App you want to share with")
        startActivity(chooserIntent)
    }



}
