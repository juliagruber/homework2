package at.fh.swengb.gruber.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var usersDb: NoteRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersDb = NoteRoomDatabase.getDatabase(applicationContext)
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val userid = sharedPreferences.getString("userid", null)
        if (userid != null) {
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
        fun saveUser(v: View) {


            if (name.text.isEmpty() ||
                age.text.isEmpty()
            ) {
                val text = "Please enter a Name and an Age!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                return
            }

            val userName = name.text.toString()
            val userAge = age.text.toString()
            val user = User(userName, userAge)
            val userid = usersDb.userDao.findUserId(userName)

            if (userid == null) {
                usersDb.userDao.insert(user)
            }

            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("username", name.text.toString()).apply()
            sharedPreferences.edit().putString("userage", age.text.toString()).apply()
            sharedPreferences.edit().putString("userid", userid.toString()).apply()

            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
            finish()
        }

}
