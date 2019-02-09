package at.fh.swengb.gruber.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {
    private lateinit var adapter: noteAdapter
    private lateinit var db: NoteRoomDatabase
    companion object {
        val EXTRA_ID_KEY = "CAMERA_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        userdata()
        db = NoteRoomDatabase.getDatabase(applicationContext)
        notes_recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = noteAdapter({
        val implicitIntent = Intent(this, AddNoteActivity::class.java)
            implicitIntent.putExtra(EXTRA_ID_KEY, it.id)
            startActivity(implicitIntent)
        },{

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Delete Note")
            dialogBuilder.setMessage("Are you sure you want to delete the Note ${it.title}?")
            dialogBuilder.setPositiveButton("Yes") { _, _ -> db.noteDao.delete(it)
                onResume() }
            dialogBuilder.setNegativeButton("No", null)
            dialogBuilder.show()


        }                    )

        notes_recycler_view.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("username",null)
        val userid = db.userDao.findUserId(userName) ?: -1

        adapter.updateNote(db.userDao.findNotesForUser(userid).notes)
    }

    fun userdata() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username",null)
        val userage = sharedPreferences.getString("userage",null)
        user.text = "Notes for $username, $userage"
    }

    fun addNote(v:View) {
        val intent = Intent(this,AddNoteActivity::class.java)
        startActivity(intent)
    }

    fun logout(v:View) {
        val logout = Intent(this, MainActivity::class.java)
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("username", null ).apply()
        sharedPreferences.edit().putString("userage", null ).apply()
        sharedPreferences.edit().putString("userid", null).apply()
        startActivity(logout)
        finish()
    }
}
