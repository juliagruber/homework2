package at.fh.swengb.gruber.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class note(val title: String, val content: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}