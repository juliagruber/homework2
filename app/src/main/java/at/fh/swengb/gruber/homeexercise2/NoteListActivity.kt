package at.fh.swengb.gruber.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {
    private lateinit var adapter: noteAdapter
    private lateinit var db: NoteRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        userdata()
        db = NoteRoomDatabase.getDatabase(applicationContext)
        notes_recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = noteAdapter()
        notes_recycler_view.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        adapter.updateNote(db.noteDao.findNotes())
    }

    fun userdata() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username",null)
        val userage = sharedPreferences.getInt("userage",0)
        user.text = "Notes for $username, $userage"
    }

    fun addNote(v:View) {
        val intent = Intent(this,AddNoteActivity::class.java)
        startActivity(intent)
    }
}
