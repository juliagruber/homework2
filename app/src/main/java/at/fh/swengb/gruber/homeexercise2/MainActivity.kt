package at.fh.swengb.gruber.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveUser (v: View) {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("username",name.text.toString()).apply()
        sharedPreferences.edit().putInt("userage",age.text.toString().toInt()).apply()
        val intent = Intent(this,NoteListActivity::class.java)
        startActivity(intent)
    }
}
