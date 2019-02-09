package at.fh.swengb.gruber.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity (tableName = "Users")
class User(val name: String, val age: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}